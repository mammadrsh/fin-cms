package com.mobyfin.cms.core.asset.dto;

import com.mobyfin.cms.core.asset.model.AssetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {
    String name;
    AssetType type;
}
