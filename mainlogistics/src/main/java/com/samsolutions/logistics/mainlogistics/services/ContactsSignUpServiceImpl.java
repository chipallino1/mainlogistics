package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service
public class ContactsSignUpServiceImpl implements ContactsSignUpService {

    private ContactsRepository contactsRepository;
    private FirmsRepository firmsRepository;
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
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
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

        if(contactDTO.getFirmName()!=null) {
            List<Firms> firmsList = firmsRepository.findDistinctByFirmNameLike(contactDTO.getFirmName());
            if(firmsList.size()>0) {
                Firms firm = firmsList.get(0);//will throw exception (if firm does not exist)
                contacts.setFirmId(firm.getId());
            }
        }
        contacts.setRole("ROLE_CONTACT_USER");
        contacts.setPasswordsId(passwords.getId());
        contactsRepository.save(contacts);

    }
}
