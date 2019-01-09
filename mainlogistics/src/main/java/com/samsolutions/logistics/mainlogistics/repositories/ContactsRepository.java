package com.samsolutions.logistics.mainlogistics.repositories;

import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository
 */
@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Contacts findByEmail(String email);

    List<Contacts> findDistinctTop5ByEmailLikeAndContactStateAndFirmId(String email, ContactState contactState, Long firmId);

    Page<Contacts> findAllByContactStateAndFirmId(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByIdDesc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByFirstName(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByFirstNameAsc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByFirstNameDesc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByLastNameAsc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByLastNameDesc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByModifiedTimeAsc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByContactStateAndFirmIdOrderByModifiedTimeDesc(ContactState contactState, Long firmId, Pageable pageable);

    Page<Contacts> findAllByEmail(String email,Pageable pageable);
}
