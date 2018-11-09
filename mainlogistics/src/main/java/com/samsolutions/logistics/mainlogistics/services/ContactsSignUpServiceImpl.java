package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsSignUpServiceImpl implements ContactsService {

    ContactsRepository contactsRepository;
    SaltHash saltHash;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }
    @Autowired
    public void setSaltHash(SaltHash saltHash) {
        this.saltHash = saltHash;
    }



    @Override
    public void save() {

    }
}
