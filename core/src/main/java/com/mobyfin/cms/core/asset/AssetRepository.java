package com.mobyfin.cms.core.asset;

import com.mobyfin.cms.core.asset.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
