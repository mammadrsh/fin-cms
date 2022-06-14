package com.mobyfin.cms.core.partner.dto;

import com.mobyfin.cms.core.partner.model.Address;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class PartnerCreationRequest {
    String firstName;
    String lastName;
    String email;
    PartnerType partnerType;
    Set<Address> addresses;
}
