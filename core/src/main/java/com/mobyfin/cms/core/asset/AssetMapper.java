package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.dto.AssetDto;
import com.mobyfin.cms.core.asset.model.Asset;
import com.mobyfin.cms.core.partner.PartnerService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { PartnerService.class })
public interface AssetMapper {

    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

    @Mapping(target = "id", ignore = true)
    Asset assetDtoToAsset(AssetDto assetDto);
}
