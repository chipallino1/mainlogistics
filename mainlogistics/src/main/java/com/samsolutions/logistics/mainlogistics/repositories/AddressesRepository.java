package com.samsolutions.logistics.mainlogistics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressesRepository extends JpaRepository<AddressesEntity,Integer>{
}
