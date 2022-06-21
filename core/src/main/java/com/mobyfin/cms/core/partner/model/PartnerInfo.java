package com.mobyfin.cms.core.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerInfo {

    @Id
    Long id;
    String companyName;

    @MapsId
    @OneToOne
    @JsonIgnore
    Partner partner;
}
