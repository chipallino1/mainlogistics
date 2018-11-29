package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import org.springframework.stereotype.Component;

@Component
public interface ContactsSignUpService {

    /**
     * Set Contacts and Passwords entities by mapping
     * @param contactDTO dto of contacts
     */
    void setContactDTO(ContactDTO contactDTO);

    /**
     * Save Passwords entity
     */
    void savePassword();

    /**
     * Save Contacts entities
     */
    void save();

    void saveUser();
}
