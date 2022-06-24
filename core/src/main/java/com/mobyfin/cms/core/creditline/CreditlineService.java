package com.mobyfin.cms.core.creditline;

import com.mobyfin.cms.core.partner.PartnerService;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditlineService {
    private CreditlineRepository creditlineRepository;
    private PartnerService partnerService;

    public Creditline newCreditline(Long partnerId, Creditline creditline) {
        Partner partner = partnerService.getPartner(partnerId);
        creditline.setPartner(partner);
        return creditlineRepository.save(creditline);
    }
}
