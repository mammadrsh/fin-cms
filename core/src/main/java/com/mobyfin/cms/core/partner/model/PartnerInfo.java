package com.mobyfin.cms.core.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerInfo {

    @Id
    Long id;
    String companyName;

    @OneToOne
    @MapsId
    @JsonIgnore
    Partner partner;
}
