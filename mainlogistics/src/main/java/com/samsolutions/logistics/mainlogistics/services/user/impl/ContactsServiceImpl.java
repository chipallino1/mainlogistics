package com.samsolutions.logistics.mainlogistics.services.user.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.user.ContactsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Contacts service
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsRepository contactsRepository;
    private UsersRepository usersRepository;

    private ContactsSignUpService contactsSignUpService;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setContactsSignUpService(ContactsSignUpService contactsSignUpService) {
        this.contactsSignUpService = contactsSignUpService;
    }

    @Override
    public ContactDTO getByEmail(String email) {
        Contacts contact = contactsRepository.findByEmail(email);
        ContactDTO contactDTO = new ContactDTO();
        map(contact, contactDTO);
        return contactDTO;
    }

    @Override
    @Transactional
    public void update(String email, ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(email);
        if(contactDTO.getImage()==null){
            contactDTO.setAvatarPath(contacts.getAvatarPath());
            Users users = usersRepository.findByEmail(email);
            users.setEmail(contactDTO.getEmail());
            map(contactDTO, contacts);
            usersRepository.save(users);
            contactsRepository.save(contacts);
        }
        else{

            contactsSignUpService.updateContact(email);
            contactsSignUpService.setContactDTO(contactDTO);
            contactsSignUpService.save();
            contactsSignUpService.saveAvatar(contactDTO.getImage());
            //contactsSignUpService.saveUser();
        }

    }

    @Override
    public String getCreatedAt(String email) {
        Contacts contacts = contactsRepository.findByEmail(email);
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
        return dateFormat.format(contacts.getCreatedAt());
    }


}
