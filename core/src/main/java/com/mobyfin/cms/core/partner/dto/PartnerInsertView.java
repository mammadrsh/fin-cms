package com.mobyfin.cms.core.partner.dto;

import com.mobyfin.cms.core.partner.model.PartnerInfo;
import com.mobyfin.cms.core.partner.model.Address;
import com.mobyfin.cms.core.partner.model.PartnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerInsertView {
    String firstName;
    String lastName;
    String email;
    PartnerType partnerType;
    Set<Address> addresses;
    PartnerInfo partnerInfo;
}
