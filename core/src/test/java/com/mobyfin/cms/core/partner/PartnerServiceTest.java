package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerCreationRequest;
import com.mobyfin.cms.core.partner.model.Partner;
import com.mobyfin.cms.core.partner.model.PartnerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    @Mock private PartnerRepository partnerRepository;
    private PartnerService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PartnerService(partnerRepository);
    }

    @Test
    void canCreatePartner() {
        // given
        PartnerCreationRequest request = new PartnerCreationRequest("Zane", "Vroly", "z.vroly@conm.com", PartnerType.DEALER);

        // when
        underTest.createPartner(request);

        // then
        ArgumentCaptor<Partner> partnerArgumentCaptor = ArgumentCaptor.forClass(Partner.class);

        verify(partnerRepository).save(partnerArgumentCaptor.capture());

        Partner capturedPartner = partnerArgumentCaptor.getValue();

//        assertThat(capturedPartner).isEqualTo(request);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // given
        PartnerCreationRequest request = new PartnerCreationRequest("Zane", "Vroly", "z.vroly@conm.com", PartnerType.DEALER);

        given(partnerRepository.existsByEmail(request.getEmail())).willReturn(true);
        // when

        // then
        assertThatThrownBy(() -> underTest.createPartner(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already exists : " + request.getEmail());

        verify(partnerRepository, never()).save(any());
    }
}