package com.samsolutions.logistics.mainlogistics.services.user;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;

import java.util.List;

public interface ContactsService {

    ContactDTO getByEmail(String email);

    void update(String email, ContactDTO contactDTO);

    void map(Object src, Object dest);

    List<ContactDTO> getContactsTop5(String firmName, String status);
}
