package com.samsolutions.logistics.mainlogistics.services.user.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsRepository contactsRepository;
    private UsersRepository usersRepository;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public ContactDTO getByEmail(String email) {
        Contacts contact = contactsRepository.findByEmail(email);
        ContactDTO contactDTO = new ContactDTO();
        map(contact, contactDTO);
        return contactDTO;
    }

    @Override
    public void update(String email, ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(email);
        Users users = usersRepository.findByEmail(email);
        users.setEmail(contactDTO.getEmail());
        map(contactDTO, contacts);
        usersRepository.save(users);
        contactsRepository.save(contacts);
    }

    @Override
    public void map(Object src, Object dest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(src, dest);
    }

    @Override
    public List<ContactDTO> getContactsTop5(String firmName, String status) {
        return null;
    }
}
