package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmsRepository extends JpaRepository<Firms,Long> {
    List<Firms> findAllByFirmNameLike(String firmName);
    List<Firms> findDistinctTop5ByFirmName(String firmName);
}
