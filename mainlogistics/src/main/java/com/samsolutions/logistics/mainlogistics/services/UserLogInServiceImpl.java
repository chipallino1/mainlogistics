package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogInServiceImpl implements UserLogInService {

    private FirmsRepository firmsRepository;
    private ContactsRepository contactsRepository;
    private SaltHash saltHash;

    private Contacts contact;
    private Firms firm;

    private ContactDTO contactDTO;
    private  FirmDTO firmDTO;

    private boolean isContact;
    private boolean isFirm;


    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }
    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }
    @Autowired
    public void setSaltHash(SaltHash saltHash) {
        this.saltHash = saltHash;
    }

    public Contacts getContact() {
        return contact;
    }

    public Firms getFirm() {
        return firm;
    }

    @Override
    public ContactDTO getContactDTO() {
        ModelMapper modelMapper=new ModelMapper();
        ContactDTO contactDTO=new ContactDTO();
        modelMapper.map(contact,contactDTO);
        return contactDTO;
    }

    @Override
    public FirmDTO getFirmDTO() {
        ModelMapper modelMapper=new ModelMapper();
        FirmDTO firmDTO=new FirmDTO();
        modelMapper.map(firm,firmDTO);
        return firmDTO;
    }

    @Override
    public boolean isContact() {
        return isContact;
    }

    @Override
    public boolean isFirm() {
        return isFirm;
    }

    @Override
    public boolean authorize(String email,String password) {

        List<Contacts> contactsList=contactsRepository.findAllByEmail(email);
        if(contactsList.size()==0){
            List<Firms> firmsList=firmsRepository.findAllByEmail(email);
            if(firmsList.size()==0){
                return false;
            }
            Passwords passwords=firmsList.get(0).getPasswordsByPasswordId();
            if(saltHash.validate(password,passwords.getPassHash(),saltHash.getBytesFromString(passwords.getSalt()))){
                isFirm=true;
                firm=firmsList.get(0);
                return true;
            }
            return false;
        }
        if(contactsList.size()==0){
            return false;
        }
        Passwords passwords=contactsList.get(0).getPasswordsByPasswordsId();
        if(saltHash.validate(password,passwords.getPassHash(),saltHash.getBytesFromString(passwords.getSalt()))){
            isContact=true;
            contact=contactsList.get(0);
            return true;
        }


        return false;

    }


}
