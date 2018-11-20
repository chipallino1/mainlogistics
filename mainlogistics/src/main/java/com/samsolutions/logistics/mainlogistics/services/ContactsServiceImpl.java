package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
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
    public List<Contacts> getAllContacts() {
        return contactsRepository.findAll();
    }

    @Override
    public ContactDTO getByEmail(String email) {
        Contacts contact = contactsRepository.findByEmail(email);
        ContactDTO contactDTO=new ContactDTO();
        map(contact,contactDTO);
        return contactDTO;
    }

    @Override
    public void update(String email,ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(email);
        Users users = usersRepository.findByEmail(email);
        users.setEmail(contactDTO.getEmail());
        map(contactDTO,contacts);
        usersRepository.save(users);
        contactsRepository.save(contacts);
    }

    @Override
    public void map(Object src, Object dest) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(src,dest);
    }
    @Override
    public List<ContactDTO> getAllByName(String email) {

        List<Contacts> contactsList = contactsRepository.findDistinctTop5ByEmailLike(email+"%");
        List<ContactDTO> contactDTOList = new ArrayList<>(contactsList.size());
        ModelMapper modelMapper = new ModelMapper();

        for(int i=0;i<contactsList.size();i++){

            contactDTOList.add(new ContactDTO());
            modelMapper.map(contactsList.get(i),contactDTOList.get(i));

        }
        return contactDTOList;

    }

}
