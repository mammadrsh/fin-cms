package com.mobyfin.cms.core.creditline;

import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerType;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CreditlineRepositoryTest {

    @Autowired
    CreditlineRepository underTest;
    @Autowired
    PartnerRepository partnerRepository;

    @Test
    void shouldFindAllByPartner_Id() {
        // given
        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .email("email@email.com")
                .build();

        Partner savedPartner = partnerRepository.save(partner);

        Creditline creditline = new Creditline().builder()
                .amount(200000L)
                .effectiveDateFrom(LocalDate.now())
                .effectiveDateTo(LocalDate.now().plusDays(120))
                .partner(partner)
                .build();

        Creditline savedCreditline = underTest.save(creditline);

        // when
        List<Creditline> creditlineList = underTest.findAllByPartner_Id(savedPartner.getId());

        // then
        assertThat(creditlineList).isNotEmpty().contains(savedCreditline);
    }

    @Test
    void shouldNotFindAllByPartner_IdNoCreditline() {
        // given
        Partner partner = Partner.builder()
                .partnerType(PartnerType.DEALER)
                .firstname("Jax")
                .lastname("Tomd")
                .email("email@email.com")
                .build();

        Partner savedPartner = partnerRepository.save(partner);

        // when
        List<Creditline> creditlineList = underTest.findAllByPartner_Id(savedPartner.getId());

        // then
        assertThat(creditlineList).isEmpty();
    }

    @Test
    void shouldNotFindAllByPartner_IdNoPartner() {
        // given
        Long id = 10L;

        // when
        List<Creditline> creditlineList = underTest.findAllByPartner_Id(id);

        // then
        assertThat(creditlineList).isEmpty();
    }
}