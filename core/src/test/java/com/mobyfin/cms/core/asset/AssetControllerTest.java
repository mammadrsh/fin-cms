package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.Utils;
import com.mobyfin.cms.core.asset.dto.AssetDto;
import com.mobyfin.cms.core.asset.model.Asset;
import com.mobyfin.cms.core.asset.model.AssetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Utils utils;

    private Asset asset;

    @BeforeEach
    void setUp() {
        utils = new Utils();

        asset = Asset.builder()
                .name("PORSCHE 911")
                .type(AssetType.CAR)
                .build();
    }

    @Test
    void shouldCreateNewAsset() throws Exception {
        // given
        AssetDto assetDto = new AssetDto();
        assetDto.setName("Porsche 911");
        assetDto.setType(AssetType.CAR);

        // when
        ResultActions createAssetResultAction = mockMvc.perform(post("/api/v1/partners/1/assets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(utils.objectToJson(assetDto))));

        // then
        createAssetResultAction.andExpect(status().isCreated())
                .andExpect(content().string(containsString(asset.getName())))
                .andExpect(content().string(containsString(asset.getType().toString())));

    }
}