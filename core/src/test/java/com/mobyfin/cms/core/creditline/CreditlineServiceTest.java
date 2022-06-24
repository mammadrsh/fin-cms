package com.mobyfin.cms.core.creditline;

import com.mobyfin.cms.core.partner.PartnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditlineServiceTest {
    CreditlineService underTest;

    @Mock
    CreditlineRepository creditlineRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void newCreditline() {
        // given

    }
}