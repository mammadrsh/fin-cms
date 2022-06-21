package com.mobyfin.cms.core.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    AddressType addressType;
    String cityName;
    String countryRegionName;
    String countyName;
    String addressLine1;
    String addressLine2;
    Integer zipcode;

    LocalDate effectiveDateFrom;
    LocalDate effectiveDateTo;

    boolean isCurrent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Partner partner;
}

