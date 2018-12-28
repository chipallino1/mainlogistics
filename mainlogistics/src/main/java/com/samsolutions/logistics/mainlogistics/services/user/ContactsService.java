package com.samsolutions.logistics.mainlogistics.services.user;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.utils.Converter;
import com.samsolutions.logistics.mainlogistics.services.utils.Pagination;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Contacts service
 */
@Component
public interface ContactsService extends Converter {

    ContactState getContactState(String email);
    /**
     * Get contact user
     * @param email contact user email
     * @return dto of contacts
     */
    ContactDTO getByEmail(String email);

    /**
     * Update contacts
     * @param email contact user email
     * @param contactDTO dto of contacts
     */
    void update(String email, ContactDTO contactDTO);

    /**
     * Get a creation year and month
     * @param email by email
     * @return string yyyy/MM
     */
    String getCreatedAt(String email);

}
