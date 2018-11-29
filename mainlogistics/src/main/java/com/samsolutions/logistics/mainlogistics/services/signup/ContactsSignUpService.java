package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import org.springframework.stereotype.Component;

/**
 * Contacts sign up service
 */
@Component
public interface ContactsSignUpService extends SignUpService {

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
