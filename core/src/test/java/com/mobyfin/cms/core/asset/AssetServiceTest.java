package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.model.Asset;
import com.mobyfin.cms.core.asset.model.AssetType;
import com.mobyfin.cms.core.partner.PartnerService;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private PartnerRepository partnerRepository;
    @Mock
    private AssetRepository assetRepository;

    @Mock
    private PartnerService partnerService;

    @Mock
    private AssetService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AssetService(assetRepository, partnerService);
    }

    @Test
    void shouldCreateNewAsset() {
        // given
        when(assetRepository.save(Mockito.any(Asset.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        when(partnerService.getPartner(1L))
                .thenAnswer(i -> Partner.builder().id(1L).build());

        Asset asset = Asset.builder()
                .name("PORSCHE 911")
                .type(AssetType.CAR)
                .build();

        // when
        Asset newAsset = underTest.newAsset(1L, asset);

        // then
        ArgumentCaptor<Asset> assetArgumentCaptor = ArgumentCaptor.forClass(Asset.class);

        verify(assetRepository).save(assetArgumentCaptor.capture());
        then(assetRepository).should().save(assetArgumentCaptor.capture());

        Asset capturedAsset = assetArgumentCaptor.getValue();
        assertThat(newAsset.getName()).isEqualTo(asset.getName());
        assertThat(capturedAsset.getName()).isEqualTo(asset.getName());
    }
}