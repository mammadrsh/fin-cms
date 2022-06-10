package com.mobyfin.cms.core.partner;

import lombok.*;

import javax.persistence.*;

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

}
