package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository
 */
@Repository
public interface FirmsRepository extends JpaRepository<Firms, Long> {

    List<Firms> findDistinctByFirmNameLike(String firmName);

    List<Firms> findDistinctTop5ByFirmNameLike(String firmName);

    List<Firms> findAllByEmail(String email);

    Firms findByFirmName(String firmName);

    Firms findByEmail(String email);

    Page<Firms> findAllByFirmNameLike(String firmName, Pageable pageable);
}
