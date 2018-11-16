package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsRepository contactsRepository;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public ContactDTO getByEmail(String email) {
        Contacts contact = contactsRepository.findByEmail(email);
        ContactDTO contactDTO=new ContactDTO();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(contact,contactDTO);
        return contactDTO;
    }
}
