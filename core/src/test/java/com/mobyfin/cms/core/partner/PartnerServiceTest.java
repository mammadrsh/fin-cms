package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.dto.PartnerCreationRequest;
import com.mobyfin.cms.core.partner.model.Partner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
        PartnerCreationRequest request = new PartnerCreationRequest();

        // when
        underTest.createPartner(request);

        // then
        ArgumentCaptor<Partner> partnerArgumentCaptor = ArgumentCaptor.forClass(Partner.class);

        verify(partnerRepository).save(partnerArgumentCaptor.capture());

        Partner capturedPartner = partnerArgumentCaptor.getValue();

//        assertThat(capturedPartner).isEqualTo(request);
    }
}