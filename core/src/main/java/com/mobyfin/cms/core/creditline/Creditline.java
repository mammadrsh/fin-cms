package com.mobyfin.cms.core.creditline;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobyfin.cms.core.partner.model.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Creditline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long amount;
    LocalDate effectiveDateFrom;
    LocalDate effectiveDateTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Partner partner;
}
