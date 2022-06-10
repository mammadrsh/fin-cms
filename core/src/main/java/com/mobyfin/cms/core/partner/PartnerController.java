package com.mobyfin.cms.core.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/partner")
public record PartnerController(PartnerService partnerService) {

    @PostMapping
    public Partner createPartner(@RequestBody PartnerCreationRequest partnerCreationRequest) {
        log.info("Creating a new partner {}", partnerCreationRequest);
        return partnerService.createPartner(partnerCreationRequest);
    }
}
