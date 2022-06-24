package com.mobyfin.cms.core.creditline.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobyfin.cms.core.partner.model.Partner;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

public class CreditlineDto {
    Long amount;
    LocalDate effectiveDateFrom;
    LocalDate effectiveDateTo;
    Partner partner;
}
