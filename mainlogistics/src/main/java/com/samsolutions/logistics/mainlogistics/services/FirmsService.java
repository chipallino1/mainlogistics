package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FirmsService {
    List<FirmDTO> getAll();
    List<FirmDTO> getAllByName(String firmName);
    FirmDTO getByEmail(String email);
    void update(String email, FirmDTO firmDTO);
    void map(Object src,Object dest);
    String addContact(ContactDTO contactDTO);
    List<ContactDTO> getContacts(String firmName);
}
