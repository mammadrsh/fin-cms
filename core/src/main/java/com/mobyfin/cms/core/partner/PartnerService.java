package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerCreationRequest;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PartnerService {
    private final PartnerRepository partnerRepository;

    public Partner createPartner(PartnerCreationRequest partnerCreationRequest) {
        PartnerInfo info = PartnerInfo.builder().companyName("Awesome Autos").build();

        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .info(info)
                .build();

        info.setPartner(partner);
        partnerRepository.save(partner);

        return partner;
    }
}
