package com.mobyfin.cms.core.partner.dto;

import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartnerCreationRequest {
    String firstName;
    String lastName;
    String email;
    PartnerType partnerType;
}
