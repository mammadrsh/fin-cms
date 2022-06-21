package com.mobyfin.cms.core.partner.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Partner {
    @Id
    @SequenceGenerator(name = "partner_id_sequence", sequenceName = "partner_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_id_sequence")
    Long id;
    PartnerType partnerType;
    String firstname;
    String lastname;
    @Column(
            unique = true,
            nullable = false
    )
    String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    PartnerInfo info;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partner")
    Set<Address> addresses = new HashSet<>();
}
