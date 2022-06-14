package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerCreationRequest;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public Partner createPartner(PartnerCreationRequest partnerCreationRequest) {
        if (partnerRepository.existsByEmail(partnerCreationRequest.getEmail()))
            throw new IllegalArgumentException("Email already exists : " + partnerCreationRequest.getEmail());

        PartnerInfo info = PartnerInfo.builder().companyName("Awesome Autos").build();
        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname(partnerCreationRequest.getFirstName())
                .lastname(partnerCreationRequest.getLastName())
                .email(partnerCreationRequest.getEmail())
                .info(info)
                .build();

        info.setPartner(partner);
        partnerRepository.save(partner);

        return partner;
    }
}
