package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.model.PartnerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerInfoRepository extends JpaRepository<PartnerInfo, Long> {
    Boolean existsByCompanyName(String companyName);
}
