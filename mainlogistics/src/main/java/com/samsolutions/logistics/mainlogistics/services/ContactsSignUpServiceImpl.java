package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class ContactsSignUpServiceImpl implements ContactsSignUpService {

    private ContactsRepository contactsRepository;
    private PasswordsRepository passwordsRepository;
    private SaltHash saltHash;
    private ContactDTO contactDTO;
    private Contacts contacts;
    private Passwords passwords;

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }
    @Autowired
    public void setSaltHash(SaltHash saltHash) {
        this.saltHash = saltHash;
    }
    @Autowired
    public void setPasswordsRepository(PasswordsRepository passwordsRepository) {
        this.passwordsRepository = passwordsRepository;
    }
    @Override
    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
        contacts=new Contacts();
        passwords=new Passwords();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(contactDTO,contacts);

    }
    public Contacts getContacts(){
        return contacts;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    @Override
    public void savePassword() {

        String password = contactDTO.getPasswordRepeat();
        byte[] saltBytes = saltHash.getSalt();
        String hashPass = saltHash.get_SHA_256_SecurePassword(password,saltBytes);
        passwords.setPassHash(hashPass);
        passwords.setSalt(saltHash.getStringFromBytes(saltBytes));
        passwordsRepository.save(passwords);

    }

    @Override
    public void save(){

        contacts.setPasswordsId(passwords.getId());
        contactsRepository.save(contacts);

    }
}
