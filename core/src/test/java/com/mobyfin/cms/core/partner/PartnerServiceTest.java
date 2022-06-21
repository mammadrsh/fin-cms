package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerInsertView;
import com.mobyfin.cms.core.partner.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class PartnerServiceTest {

    @Mock private PartnerRepository partnerRepository;
    @Mock private AddressRepository addressRepository;
    private PartnerService underTest;

    Partner partner;

    @BeforeEach
    void setUp() {
        underTest = new PartnerService(partnerRepository, addressRepository);

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
                .info(info)
                .addresses(new HashSet<>(Collections.singletonList(address)))
                .build();
    }

    @Test
    void canCreatePartner() {
        // given
        PartnerInsertView request = new PartnerInsertView();
        request.setFirstName(partner.getFirstname());
        request.setPartnerInfo(partner.getInfo());
        request.setAddresses(partner.getAddresses());

        when(partnerRepository.save(Mockito.any(Partner.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // when
        Partner createdPartner = underTest.createPartner(request);

        // then
        ArgumentCaptor<Partner> partnerArgumentCaptor = ArgumentCaptor.forClass(Partner.class);

        verify(partnerRepository).save(partnerArgumentCaptor.capture());
        then(partnerRepository).should().save(partnerArgumentCaptor.capture());

        Partner capturedPartner = partnerArgumentCaptor.getValue();
        assertThat(createdPartner.getInfo()).isNotNull();
        assertThat(capturedPartner.getInfo()).isNotNull();
        assertThat(createdPartner.getAddresses()).isNotNull();
        assertThat(capturedPartner.getAddresses()).isNotNull();
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        PartnerInsertView request = new PartnerInsertView();

        given(partnerRepository.existsByEmail(request.getEmail())).willReturn(true);
        // when

        // then
        assertThatThrownBy(() -> underTest.createPartner(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already exists : " + request.getEmail());

        verify(partnerRepository, never()).save(any());
    }

    @Test
    void canGetPartners() {
        // when
        underTest.getPartners();
        // then
        verify(partnerRepository).findAll();
    }
}