package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerInsertView;
import com.mobyfin.cms.core.partner.model.Address;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;
    private final AddressRepository addressRepository;

    public Partner createPartner(PartnerInsertView insertView) {
        if (partnerRepository.existsByEmail(insertView.getEmail()))
            throw new IllegalArgumentException("Email already exists : " + insertView.getEmail());

        PartnerInfo info = insertView.getPartnerInfo();
        Set<Address> addresses = insertView.getAddresses();
        Partner partner = new Partner();
        partner.setFirstname(insertView.getFirstName());
        partner.setLastname(insertView.getLastName());
        partner.setEmail(insertView.getEmail());
        info.setPartner(partner);
        partner.setInfo(info);
        Partner savedPartner = partnerRepository.save(partner);

        addresses.forEach(address -> {
            address.setPartner(savedPartner);
            addressRepository.save(address);
        });

        savedPartner.setAddresses(addresses);
        return savedPartner;
    }

    public List<Partner> getPartners() {
        return partnerRepository.findAll();
    }
}
