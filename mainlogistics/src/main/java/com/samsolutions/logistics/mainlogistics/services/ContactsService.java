package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;

import java.util.List;

public interface ContactsService {
    List<Contacts> getAllContacts();
    ContactDTO getByEmail(String email);
    void update(ContactDTO contactDTO);
    void map(Object src,Object dest);
}
