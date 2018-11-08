package com.samsolutions.logistics.mainlogistics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesInfoRepository extends JpaRepository<RoutesInfoEntity, Long> {
}
