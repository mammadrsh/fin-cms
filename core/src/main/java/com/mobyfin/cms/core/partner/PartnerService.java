package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.Address;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.repository.AddressRepository;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.springframework.beans.BeanUtils.copyProperties;

@AllArgsConstructor
@Service
@Slf4j
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final AddressRepository addressRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Partner getPartner(Long id) {
        return partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner with not found id: " + id));
    }

    public List<Partner> getPartners() {
        return partnerRepository.findAll();
    }

    @Transactional
    public Partner createPartner(Partner partner) {
        if (partnerRepository.existsByEmail(partner.getEmail()))
            throw new IllegalArgumentException("Email already exists : " + partner.getEmail());

        PartnerInfo info = partner.getPartnerInfo();
        Set<Address> addresses = partner.getAddresses();
        info.setPartner(partner);
        Partner savedPartner = partnerRepository.save(partner);

//        addresses.forEach(address -> {
//            address.setPartner(savedPartner);
//            addressRepository.save(address);
//        });
//
//        savedPartner.setAddresses(addresses);

        // todo: send notification
        try {
            kafkaTemplate.send("notification-topic", partner.getEmail());
        } catch (InvalidTopicException e) {
            log.info("Error in notification");
        }

        return savedPartner;
    }

    @Transactional
    public Partner updatePartner(Long id, Partner newPartner) {
        Partner partner = partnerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Partner with not found id: " + id));

        copyProperties(newPartner, partner, "id");

        return partnerRepository.save(partner);
    }
}
