package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
