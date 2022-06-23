package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.PartnerType;
import com.mobyfin.cms.core.partner.repository.PartnerInfoRepository;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PartnerInfoRepositoryTest {

    @Autowired
    private PartnerInfoRepository underTest;
    @Autowired
    private PartnerRepository partnerRepository;

    @AfterEach
    void tearDown() {
        partnerRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfPartnerInfoCompanyNameExists() {
        // given
        String companyName = "Awesome Autos";
        PartnerInfo info = PartnerInfo.builder().companyName(companyName).build();

        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .email("test@test.com")
                .partnerInfo(info)
                .build();

        info.setPartner(partner);
        partnerRepository.save(partner);

        // when
        boolean expected = underTest.existsByCompanyName(companyName);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfPartnerInfoCompanyNameDoesNotExists() {
        // given
        String companyName = "Awesome Autos";

        // when
        boolean expected = underTest.existsByCompanyName(companyName);

        // then
        assertThat(expected).isFalse();
    }
}