package com.samsolutions.logistics.mainlogistics.services.user;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Contacts service
 */
@Component
public interface ContactsService {
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
     * Map dto on entity and vice versa
     * @param src source
     * @param dest destination
     */
    void map(Object src, Object dest);

}
