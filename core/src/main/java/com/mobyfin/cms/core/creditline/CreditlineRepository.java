package com.mobyfin.cms.core.creditline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditlineRepository extends JpaRepository<Creditline, Long> {
    List<Creditline> findAllByPartner_Id(Long id);
}
