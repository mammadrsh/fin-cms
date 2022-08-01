package com.mobyfin.cms.core.asset.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobyfin.cms.core.partner.model.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @SequenceGenerator(name = "asset_id_sequence", sequenceName = "asset_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asset_id_sequence")
    Long id;
    String name;
    AssetType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Partner partner;
}
