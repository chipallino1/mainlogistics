package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordsRepository extends JpaRepository<Passwords, Long> {

    List<Passwords> findDistinctTop1ByPassHashLike(String passHash);

}
