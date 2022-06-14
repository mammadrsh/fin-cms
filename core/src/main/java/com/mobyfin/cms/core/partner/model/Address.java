package com.mobyfin.cms.core.partner.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Address {

    @Id
    Long id;
    AddressType addressType;
    String cityName;
    String countryRegionName;
    String countyName;
    String addressLine1;
    String addressLine2;
    Integer zipcode;

    @Temporal(TemporalType.DATE)
    Date effectiveDateFrom;
    @Temporal(TemporalType.DATE)
    Date effectiveDateTo;

    boolean isCurrent;

    @ManyToOne
    Partner partner;
}

