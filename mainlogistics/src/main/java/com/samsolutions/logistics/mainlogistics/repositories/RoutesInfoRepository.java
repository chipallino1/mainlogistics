package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.RoutesInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 */
@Repository
public interface RoutesInfoRepository extends JpaRepository<RoutesInfo, Long> {
}
