package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.dto.AssetDto;
import com.mobyfin.cms.core.asset.model.Asset;
import com.mobyfin.cms.core.asset.model.AssetType;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AssetMapperTest {

    AssetMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = AssetMapper.INSTANCE;
    }

    @Test
    void shouldMapAssetDtoToAsset() {
        // given
        Partner partner = new Partner().builder()
                .id(1L)
                .firstname("Morgan")
                .lastname("Esmiman")
                .partnerType(PartnerType.DEALER)
                .email("morgan.e@gmail.com")
                .build();

        AssetDto assetDto = new AssetDto();
        assetDto.setName("PORSCHE 911");
        assetDto.setType(AssetType.CAR);

        // when
        Asset asset = underTest.assetDtoToAsset(assetDto);
        log.info(assetDto.toString());
        log.info(asset.toString());

        // then
        assertThat(asset).isNotNull();
        assertThat(asset.getName()).isEqualTo(assetDto.getName());
        assertThat(asset.getType()).isEqualTo(assetDto.getType());
    }
}