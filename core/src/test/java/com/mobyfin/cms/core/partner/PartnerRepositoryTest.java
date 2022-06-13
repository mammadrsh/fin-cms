package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.PartnerType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository underTest;

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
                .info(info)
                .build();

        info.setPartner(partner);
        underTest.save(partner);

        // when
        underTest.existsByEmail(email);

        // then
        assertThat(underTest.existsByEmail(email)).isTrue();
    }

    @Test
    void itShouldCheckIfPartnerDoesNotExistsByEmail() {
        // given
        String email = "test@test.com";

        // when
        underTest.existsByEmail(email);

        // then
        assertThat(underTest.existsByEmail(email)).isFalse();
    }
}