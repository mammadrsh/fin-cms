package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.model.Asset;
import com.mobyfin.cms.core.partner.PartnerService;
import com.mobyfin.cms.core.partner.model.Partner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AssetService {
    private final AssetRepository assetRepository;
    private final PartnerService partnerService;

    public Asset newAsset(Long partnerId, Asset asset) {
        Partner partner = partnerService.getPartner(partnerId);
        log.info(partner.toString());
        log.info(asset.toString());

        asset.setPartner(partner);
        assetRepository.save(asset);

        return asset;
    }
}
