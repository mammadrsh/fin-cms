package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "firstName", target = "firstname")
    @Mapping(source = "lastName", target = "lastname")
    Partner partnerDtoToPartner(PartnerDto partnerDto);

    @Mapping(source = "firstname", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    PartnerDto partnerToPartnerDto(Partner partner);
}
