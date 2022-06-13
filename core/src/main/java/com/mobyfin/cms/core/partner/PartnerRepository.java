package com.mobyfin.cms.core.partner;

import com.mobyfin.cms.core.partner.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
}
