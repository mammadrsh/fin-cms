package com.mobyfin.cms.core.partner;

import org.springframework.stereotype.Service;

@Service
public record PartnerService(PartnerRepository partnerRepository) {
    public Partner createPartner(PartnerCreationRequest partnerCreationRequest) {
        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .build();

        partnerRepository.save(partner);

        return partner;
    }
}
