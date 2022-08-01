package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.dto.AssetDto;
import com.mobyfin.cms.core.asset.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final AssetMapper assetMapper;

    @Autowired
    public AssetController(AssetService assetService, AssetMapper assetMapper) {
        this.assetService = assetService;
        this.assetMapper = assetMapper;
    }

    @RequestMapping("/api/v1/partners/{id}/assets")
    public ResponseEntity<Asset> newAsset(@PathVariable(name = "id") Long partnerId, @RequestBody AssetDto assetDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assetService.newAsset(partnerId, assetMapper.assetDtoToAsset(assetDto)));
    }
}
