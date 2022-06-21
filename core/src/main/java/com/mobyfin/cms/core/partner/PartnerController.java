package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerInsertView;
import com.mobyfin.cms.core.partner.model.Partner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/partner")
public class PartnerController {

    private final PartnerService partnerService;
    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    public List<Partner> getPartners() {
        log.info("Getting all partners");
        return partnerService.getPartners();
    }

    @PostMapping
    public Partner createPartner(@RequestBody PartnerInsertView partnerInsertView) {
        log.info("Creating a new partner {}", partnerInsertView);
        return partnerService.createPartner(partnerInsertView);
    }
}
