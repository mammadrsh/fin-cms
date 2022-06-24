package com.mobyfin.cms.core.creditline;

import com.mobyfin.cms.core.creditline.dto.CreditlineDto;
import com.mobyfin.cms.core.partner.PartnerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreditlineMapper {
    CreditlineMapper INSTANCE = Mappers.getMapper(CreditlineMapper.class);

    @Mapping(target = "id", ignore = true)
    Creditline creditlineDtoToCreditline(CreditlineDto creditlineDto);

    CreditlineDto creditlineToCreditlineDto(CreditlineDto creditlineDto);
}
