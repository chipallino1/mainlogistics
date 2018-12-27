package com.samsolutions.logistics.mainlogistics.services.signup.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.entities.Contacts;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.ContactsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.ContactState;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.security.UserState;
import com.samsolutions.logistics.mainlogistics.services.signup.ContactsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.utils.FileStorageService;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.FirmNotFoundException;
import com.samsolutions.logistics.mainlogistics.validation.exceptions.MainException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Contact user class that provided services for contact users
 */
@Service
public class ContactsSignUpServiceImpl implements ContactsSignUpService {

    private ContactsRepository contactsRepository;
    private FirmsRepository firmsRepository;
    private PasswordsRepository passwordsRepository;
    private UsersRepository usersRepository;
    private SaltHash saltHash;
    private FileStorageService fileStorageService;
    private ContactDTO contactDTO;
    private Contacts contacts;
    private Passwords passwords;
    private Users users;


    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Autowired
    public void setFirmsRepository(FirmsRepository firmsRepository) {
        this.firmsRepository = firmsRepository;
    }

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setSaltHash(SaltHash saltHash) {
        this.saltHash = saltHash;
    }

    @Autowired
    public void setPasswordsRepository(PasswordsRepository passwordsRepository) {
        this.passwordsRepository = passwordsRepository;
    }
    @Autowired
    public void setFileStorageService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    @Override
    public void updateContact(String email) {
        this.contacts=contactsRepository.findByEmail(email);
        this.passwords=passwordsRepository.findById(this.contacts.getPasswordsId()).get();
        this.users=usersRepository.findByEmail(email);
    }

    @Override
    public void createNewContact() {
        contacts = new Contacts();
        passwords = new Passwords();
        users = new Users();
    }

    @Override
    public void setContactDTO(ContactDTO contactDTO) {
        this.contactDTO = contactDTO;
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(contactDTO, contacts);
    }

    @Override
    @Transactional
    public void saveContact() {
        savePassword();
        save();
        saveAvatar(contactDTO.getImage());
        saveUser();
    }

    public Contacts getContacts() {
        return contacts;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public void savePassword() {
        String password = contactDTO.getPasswordRepeat();
        byte[] saltBytes = saltHash.getSalt();
        String hashPass = saltHash.get_SHA_256_SecurePassword(password, saltBytes);
        passwords.setPassHash(hashPass);
        passwords.setSalt(saltHash.getStringFromBytes(saltBytes));
        passwordsRepository.save(passwords);
    }

    @Override
    public void save() {
        if (!contactDTO.getFirmName().equals("")) {
            List<Firms> firmsList = firmsRepository.findDistinctByFirmNameLike(contactDTO.getFirmName());
            if (firmsList.size() > 0) {
                Firms firm = firmsList.get(0);
                contacts.setFirmId(firm.getId());
                contacts.setContactState(ContactState.WAIT);
            }
            else {
                throw new FirmNotFoundException("Firm you chose not found","Cause: this firm is not exists");
            }
        }
        contacts.setPasswordsId(passwords.getId());
        contacts.setCreatedAt(new Date(System.currentTimeMillis()));
        contacts.setModifiedTime(new Date(System.currentTimeMillis()));
        contactsRepository.save(contacts);
    }

    @Override
    public void saveUser() {
        users.setEmail(contacts.getEmail());
        users.setUserState(UserState.ENABLED);
        users.setRole(Role.ROLE_CONTACT_USER);
        users.setPasswordId(passwords.getId());
        usersRepository.save(users);
    }

    @Override
    public void saveAvatar(MultipartFile file) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM") ;
        String createdAt=dateFormat.format(contacts.getCreatedAt());
        String avatarPath=fileStorageService.storeFile(file,createdAt,contacts.getEmail());
        contacts.setAvatarPath(avatarPath);
        contactsRepository.save(contacts);
    }


}
