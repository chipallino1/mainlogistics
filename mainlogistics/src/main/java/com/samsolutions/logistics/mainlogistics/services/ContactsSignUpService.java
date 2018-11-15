package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import org.springframework.stereotype.Component;

@Component
public interface ContactsSignUpService {

    void setContactDTO(ContactDTO contactDTO);
    void savePassword();
    void save();
}
