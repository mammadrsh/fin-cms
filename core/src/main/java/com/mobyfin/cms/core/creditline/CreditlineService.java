package com.mobyfin.cms.core.creditline;

import com.mobyfin.cms.core.partner.PartnerService;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class CreditlineService {
    private CreditlineRepository creditlineRepository;
    private PartnerRepository partnerRepository;

    public Creditline newCreditline(Long partnerId, Creditline creditline) {
        Partner partner = partnerRepository.findById(partnerId).orElseThrow(() -> new IllegalArgumentException("Partner with not found id: " + partnerId));

        creditline.setPartner(partner);
        return creditlineRepository.save(creditline);
    }

    public Creditline getCreditline(Long id) {
        return creditlineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Creditline with not found id: " + id));
    }

    public List<Creditline> getCreditlinesForPartner(Long partnerId) {
        return creditlineRepository.findAllByPartner_Id(partnerId);
    }

    @Transactional
    public Creditline updateCreditline(Long id, Creditline newCreditline) {
        Creditline creditline =  creditlineRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Creditline with not found id: " + id));

        copyProperties(newCreditline, creditline, "id");

        return creditlineRepository.save(creditline);
    }
}
