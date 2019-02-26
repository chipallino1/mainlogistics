package com.samsolutions.logistics.mainlogistics.services.signup.impl;

import com.samsolutions.logistics.mainlogistics.dto.ContactDTO;
import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.events.FirmAddingEvent;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.security.UserState;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.utils.FileStorageService;
import com.samsolutions.logistics.mainlogistics.services.utils.ImageStorageJsfService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.Part;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Firm user class that provided services for firm users
 */
@Service
public class FirmsSignUpServiceImpl implements FirmsSignUpService {

    private FirmsRepository firmsRepository;
    private PasswordsRepository passwordsRepository;
    private SaltHash saltHash;
    private FirmDTO firmDTO;
    private Firms firms;
    private Passwords passwords;
    private UsersRepository usersRepository;
    private FileStorageService fileStorageService;
    @Inject
    private ImageStorageJsfService imageStorageJsfService;
    @Inject
    private ApplicationEventPublisher applicationEventPublisher;

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

    @Autowired
    public void setUsersRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Autowired
    public void setFileStorageService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @Override
    @Transactional
    public void store(FirmDTO firmDTO) {
        createNew();
        setFirmDTO(firmDTO);
        saveFirm();
        FirmAddingEvent firmAddingEvent=new FirmAddingEvent(this,firms.getFirmName());
        applicationEventPublisher.publishEvent(firmAddingEvent);
    }

    @Override
    public void updateFirm(String email){
        this.firms = firmsRepository.findAllByEmail(email).get(0);
        this.passwords = passwordsRepository.findById(this.firms.getPasswordId()).get();
    }
    @Override
    public void createNew(){
        firms = new Firms();
        passwords = new Passwords();
    }

    @Override
    public void setFirmDTO(FirmDTO firmDTO) {
        this.firmDTO = firmDTO;
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(firmDTO, firms);

    }

    @Override
    @Transactional
    public void saveFirm() {
        savePassword(saltHash,passwordsRepository,passwords,firmDTO.getPassword());
        save();
        if(firmDTO.getImage() == null){
            saveAvatar(firmDTO.getPartImage());
        }
        else {
            saveAvatar(firmDTO.getImage());
        }
        saveUser();
    }

    public Firms getFirms() {
        return firms;
    }

    public Passwords getPasswords() {
        return passwords;
    }


    @Override
    public void save() {
        firms.setPasswordId(passwords.getId().longValue());
        firms.setCreatedAt(new Date(System.currentTimeMillis()));
        firmsRepository.save(firms);

    }

    @Override
    public void saveUser() {
        Users users = new Users();
        users.setEmail(firms.getEmail());
        users.setUserState(UserState.ENABLED);
        if(firms.getFirmType().equals("logistic")){
            users.setRole(Role.ROLE_LOGISTIC_FIRM_USER);
        }
        else {
            users.setRole(Role.ROLE_SIMPLE_FIRM_USER);
        }
        users.setPasswordId(passwords.getId().longValue());
        usersRepository.save(users);
    }

    @Override
    public void saveAvatar(MultipartFile file) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
        String avatarPath = fileStorageService.storeFile(file,dateFormat.format(firms.getCreatedAt()),firms.getEmail());
        firms.setAvatarPath(avatarPath);
        firmsRepository.save(firms);
    }

    @Override
    public void saveAvatar(Part file) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
        String avatarPath = imageStorageJsfService.storeImage(file,dateFormat.format(firms.getCreatedAt()),firms.getEmail());
        firms.setAvatarPath(avatarPath);
        firmsRepository.save(firms);
    }
}
