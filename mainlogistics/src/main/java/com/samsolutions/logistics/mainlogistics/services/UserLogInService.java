package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.stereotype.Component;

@Component
public interface UserLogInService {

    boolean isContact();
    boolean isFirm();
    boolean authorize(String email,String password);
    Contacts getContact();
    Firms getFirm();
    ContactDTO getContactDTO();
    FirmDTO getFirmDTO();

}
