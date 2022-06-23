package com.mobyfin.cms.core.partner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mobyfin.cms.core.partner.dto.PartnerDto;
import com.mobyfin.cms.core.partner.model.*;
import com.mobyfin.cms.core.partner.repository.PartnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.fail;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class PartnerControllerTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private MockMvc mockMvc;

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
    void createPartner() throws Exception {
        // given
        PartnerDto request = new PartnerDto();
        request.setFirstName(partner.getFirstname());
        request.setEmail(partner.getEmail());
        request.setPartnerInfo(partner.getPartnerInfo());
        request.setAddresses(partner.getAddresses());

        // when
        ResultActions createPartnerResultAction = mockMvc.perform(post("/api/v1/partner")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Objects.requireNonNull(objectToJson(request))));

        // then
        createPartnerResultAction
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(partner.getEmail())))
                .andExpect(content().string(containsString(partner.getPartnerInfo().getCompanyName())))
                .andExpect(content().string(containsString(partner.getAddresses().stream().findFirst().get().getAddressLine1())));

        Partner pe = partnerRepository.findById(1L).orElseThrow();
        assertThat(partnerRepository.findById(1L))
                .isPresent()
                .hasValueSatisfying(p -> assertThat(p.getEmail()).isEqualTo(partner.getEmail()))
                .hasValueSatisfying(p -> assertThat(p.getPartnerInfo().getCompanyName()).isEqualTo(partner.getPartnerInfo().getCompanyName()))
                .hasValueSatisfying(p -> assertThat(p.getPartnerInfo().getId()).isNotNull())
                .hasValueSatisfying(p -> assertThat(p.getAddresses().isEmpty()).isFalse())
                .hasValueSatisfying(p -> assertThat(p.getAddresses().stream().findFirst().get().getPartner()).isNotNull());
    }

    private String objectToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}