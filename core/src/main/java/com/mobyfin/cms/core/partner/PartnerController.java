package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.Partner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/partner")
public class PartnerController {

    private final PartnerService partnerService;
    private final PartnerMapper partnerMapper;
    @Autowired
    public PartnerController(PartnerService partnerService, PartnerMapper partnerMapper) {
        this.partnerService = partnerService;
        this.partnerMapper = partnerMapper;
    }

    @GetMapping
    public ResponseEntity<List<Partner>> getPartners() {
        log.info("Getting all partners");
        return ResponseEntity.ok(partnerService.getPartners());
    }

    @PostMapping
    public ResponseEntity<Partner> createPartner(@RequestBody PartnerDto partnerDto) {
        log.info("Creating a new partner {}", partnerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(partnerService.createPartner(partnerMapper.partnerDtoToPartner(partnerDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partner> updatePartner(@PathVariable(name = "id") Long id, @RequestBody PartnerDto partnerDto) {
        log.info("Updating partner id {} data {}", id, partnerDto);
        return ResponseEntity.ok(partnerService.updatePartner(id, partnerMapper.partnerDtoToPartner(partnerDto)));
    }
}
