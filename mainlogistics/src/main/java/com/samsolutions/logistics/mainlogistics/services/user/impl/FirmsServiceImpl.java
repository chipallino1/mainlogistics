package com.samsolutions.logistics.mainlogistics.services.user.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.user.FirmsService;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FirmNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Firms service
 */
@Service
public class FirmsServiceImpl implements FirmsService {

    private FirmsRepository firmsRepository;
    private UsersRepository usersRepository;
    private ContactsRepository contactsRepository;

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }


    @Override
    public List<FirmDTO> getAll() {

        return null;
    }

    @Override
    public List<FirmDTO> getAllByName(String firmName) {
        List<Firms> firmsList = firmsRepository.findDistinctTop5ByFirmNameLike(firmName + "%");
        List<FirmDTO> firmDTOList = new ArrayList<>(firmsList.size());
        ModelMapper modelMapper = new ModelMapper();

        for (int i = 0; i < firmsList.size(); i++) {

            firmDTOList.add(new FirmDTO());
            modelMapper.map(firmsList.get(i), firmDTOList.get(i));

        }
        return firmDTOList;

    }

    @Override
    public FirmDTO getByEmail(String email) {
        FirmDTO firmDTO = new FirmDTO();
        Firms firm = firmsRepository.findAllByEmail(email).get(0);
        map(firm, firmDTO);
        return firmDTO;
    }

    @Override
    public void update(String email, FirmDTO firmDTO) {
        Firms firms = firmsRepository.findAllByEmail(email).get(0);
        Users users = usersRepository.findByEmail(email);
        users.setEmail(firmDTO.getEmail());
        map(firmDTO, firms);
        usersRepository.save(users);
        firmsRepository.save(firms);
    }

    @Override
    public void map(Object src, Object dest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(src, dest);
    }

    @Override
    public void addContact(ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(contactDTO.getEmail());
        Firms firms = firmsRepository.findAllByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get(0);
        contacts.setContactState(ContactState.ADDED);
        Users users = usersRepository.findByEmail(contactDTO.getEmail());
        users.setRole(Role.ROLE_CONTACT_LOGISTICS_FIRM_USER);
        contactsRepository.save(contacts);
    }

    @Override
    public void deleteContact(ContactDTO contactDTO) {
        Contacts contacts = contactsRepository.findByEmail(contactDTO.getEmail());
        contacts.setContactState(ContactState.WAIT);
        Users users = usersRepository.findByEmail(contactDTO.getEmail());
        users.setRole(Role.ROLE_CONTACT_USER);
        contactsRepository.save(contacts);
    }

    @Override
    public List<ContactDTO> getContacts(String firmName, String state) {
        Firms firms = firmsRepository.findByFirmName(firmName);
        List<Contacts> contactsList=contactsRepository.findAllByContactStateAndFirmIdOrderByIdDesc(ContactState.valueOf(state),firms.getId());
        List<ContactDTO> contactDTOList = new ArrayList<>();
        ContactDTO contactDTO;
        for (int i = 0; i < contactsList.size(); i++) {
            if (contactsList.get(i).getContactState() == ContactState.valueOf(state)) {
                contactDTO = new ContactDTO();
                map(contactsList.get(i), contactDTO);
                contactDTOList.add(contactDTO);
            }
        }
        return contactDTOList;
    }
}
