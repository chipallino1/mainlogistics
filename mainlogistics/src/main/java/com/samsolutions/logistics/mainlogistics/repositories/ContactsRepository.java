package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts,Long>{
    List<Contacts> findAllByEmail(String email);
    Contacts findByEmail(String email);
    List<Contacts> findDistinctTop5ByEmailLikeAndStatusAndFirmId(String email,String status,Long firmId);
    List<Contacts> findDistinctTop5ByStatusAndFirmId(String status,Long firmId);
}
