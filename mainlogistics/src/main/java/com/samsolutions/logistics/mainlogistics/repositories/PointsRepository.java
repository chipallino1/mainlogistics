package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Points;
import com.samsolutions.logistics.mainlogistics.entities.Routes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PointsRepository extends JpaRepository<Points,Long> {
    Points findByCountryAndCityAndRegion(String country,String city, String region);
    @Query(value = "SELECT * FROM POINTS WHERE COUNTRY = ?1",
            countQuery = "SELECT count(*) FROM POINTS WHERE COUNTRY = ?1",
            nativeQuery = true)
    Page<Points> findAllPag(String country, Pageable pageable);
}
