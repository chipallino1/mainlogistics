package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import org.springframework.stereotype.Component;

/**
 * Contacts sign up service
 */
@Component
public interface ContactsSignUpService extends SignUpService {

    /**
     * Store contact in db
     * @param contactDTO contact info
     */
    void store(ContactDTO contactDTO);
    /**
     * update existing contact
     */
    void updateContact(String email);
    /**
     * create new contact and new password
     */
    void createNewContact();
    /**
     * Set Contacts and Passwords entities by mapping
     * @param contactDTO dto of contacts
     */
    void setContactDTO(ContactDTO contactDTO);

    /**
     * Save contact user
     */
    void saveContact();
}
