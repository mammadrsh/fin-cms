package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfPartnerExistsByEmail() {
        // given
        String email = "test@test.com";
        PartnerInfo info = PartnerInfo.builder().companyName("Awesome Autos").build();

        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .email(email)
//                .info(info)
                .build();

        info.setPartner(partner);
        underTest.save(partner);

        // when
        Boolean expected = underTest.existsByEmail(email);

        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfPartnerDoesNotExistsByEmail() {
        // given
        String email = "test@test.com";

        // when
        boolean expected = underTest.existsByEmail(email);

        // then
        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckIfPartnerDoesNotExistsByEmailGivenNull() {
        // given
        String email = null;

        // when
        boolean expected = underTest.existsByEmail(null);

        // then
        assertThat(expected).isFalse();
    }
}