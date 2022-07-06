package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.notification.NotificationInterface;
import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.*;
import com.mobyfin.cms.core.partner.repository.AddressRepository;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    @Mock private PartnerRepository partnerRepository;
    @Mock private AddressRepository addressRepository;
    @Mock private NotificationInterface notificationInterface;
    private PartnerService underTest;

    Partner partner;

    @BeforeEach
    void setUp() {
        underTest = new PartnerService(partnerRepository, addressRepository, notificationInterface);

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
                .addresses(new HashSet<>(Collections.singletonList(address)))
                .build();
    }

    @Test
    void canCreatePartner() {
        // given
        when(partnerRepository.save(Mockito.any(Partner.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // when
        Partner createdPartner = underTest.createPartner(partner);

        // then
        ArgumentCaptor<Partner> partnerArgumentCaptor = ArgumentCaptor.forClass(Partner.class);

        verify(partnerRepository).save(partnerArgumentCaptor.capture());
        then(partnerRepository).should().save(partnerArgumentCaptor.capture());

        Partner capturedPartner = partnerArgumentCaptor.getValue();
        assertThat(createdPartner.getPartnerInfo()).isNotNull();
        assertThat(capturedPartner.getPartnerInfo()).isNotNull();
        assertThat(createdPartner.getAddresses()).isNotNull();
        assertThat(capturedPartner.getAddresses()).isNotNull();
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given

        given(partnerRepository.existsByEmail(partner.getEmail())).willReturn(true);
        // when

        // then
        assertThatThrownBy(() -> underTest.createPartner(partner))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already exists : " + partner.getEmail());

        verify(partnerRepository, never()).save(any());
    }

    @Test
    void canGetPartners() {
        // when
        underTest.getPartners();
        // then
        verify(partnerRepository).findAll();
    }

    @Test
    void canUpdatePartner() {
        // given
        Long id = 10L;
        String newFirstName = "Ahmad";
        given(partnerRepository.findById(id)).willReturn(Optional.ofNullable(partner));
        when(partnerRepository.save(Mockito.any(Partner.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        // when
        partner.setFirstname(newFirstName);
        Partner createdPartner = underTest.updatePartner(id, partner);

        // then
        ArgumentCaptor<Partner> partnerArgumentCaptor = ArgumentCaptor.forClass(Partner.class);
        verify(partnerRepository).save(partnerArgumentCaptor.capture());
        Partner capturedPartner = partnerArgumentCaptor.getValue();
        assertThat(createdPartner.getFirstname()).isEqualTo(newFirstName);
        assertThat(capturedPartner.getFirstname()).isEqualTo(newFirstName);
    }

    @Test
    void shouldGetPartner() {
        // given
        Long id = 10L;
        given(partnerRepository.findById(id)).willReturn(Optional.ofNullable(partner));

        // when
        underTest.getPartner(id);

        // then
        verify(partnerRepository).findById(id);
    }
}