package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import org.springframework.stereotype.Component;

@Component
public interface ContactsSignUpService {

    void setContactDTO(ContactDTO contactDTO);
    void savePassword();
    void save();
}
