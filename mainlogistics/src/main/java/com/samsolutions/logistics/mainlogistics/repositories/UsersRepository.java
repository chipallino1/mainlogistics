package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
