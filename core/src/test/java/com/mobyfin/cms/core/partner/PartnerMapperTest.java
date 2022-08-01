package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PartnerMapperTest {

    Partner partner;

    @BeforeEach
    void setUp() {
        PartnerInfo info = new PartnerInfo().builder()
                .companyName("Morgan X models")
                .build();
        Address address = new Address().builder()
                .addressType(AddressType.BUSINESS)
                .cityName("Ahwaz")
                .countryRegionName("Khuz")
                .countyName("Zimbabwe")
                .addressLine1("avn")
                .zipcode(12345)
                .effectiveDateFrom(LocalDate.now())
                .effectiveDateTo(LocalDate.now())
                .isCurrent(true)
                .build();

        partner = new Partner().builder()
                .firstname("Morgan")
                .lastname("Esmiman")
                .partnerType(PartnerType.DEALER)
                .email("morgan.e@gmail.com")
                .partnerInfo(info)
                .addresses(new HashSet<>(Arrays.asList(address)))
                .build();
    }

    @Test
    void shouldMapPartnerDtoToPartner() {
        // given
        PartnerDto dto = new PartnerDto();
        dto.setFirstName("Kevin");
        dto.setEmail("mail@mail.com");
        dto.setPartnerInfo(partner.getPartnerInfo());
        dto.setAddresses(partner.getAddresses());

        // when
        Partner newPartner = PartnerMapper.INSTANCE.partnerDtoToPartner(dto);

        // then
        assertThat(newPartner).isNotNull();
        assertThat(newPartner.getFirstname()).isEqualTo(dto.getFirstName());
        assertThat(newPartner.getLastname()).isEqualTo(dto.getLastName());
        assertThat(newPartner.getEmail()).isEqualTo(dto.getEmail());
        assertThat(newPartner.getPartnerType()).isEqualTo(dto.getPartnerType());
        assertThat(newPartner.getPartnerInfo()).isEqualTo(dto.getPartnerInfo());
        assertThat(newPartner.getAddresses()).isEqualTo(dto.getAddresses());
    }

    @Test
    void shouldMapPartnerToPartnerDto() {
        // given

        // when
        PartnerDto partnerDto = PartnerMapper.INSTANCE.partnerToPartnerDto(partner);

        // then
        assertThat(partnerDto).isNotNull();
        assertThat(partnerDto.getFirstName()).isEqualTo(partner.getFirstname());
        assertThat(partnerDto.getLastName()).isEqualTo(partner.getLastname());
        assertThat(partnerDto.getEmail()).isEqualTo(partner.getEmail());
        assertThat(partnerDto.getPartnerType()).isEqualTo(partner.getPartnerType());
        assertThat(partnerDto.getPartnerInfo()).isEqualTo(partner.getPartnerInfo());
        assertThat(partnerDto.getAddresses()).isEqualTo(partner.getAddresses());

    }
}