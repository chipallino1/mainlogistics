package com.samsolutions.logistics.mainlogistics.services.signup.impl;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder.SaltHash;
import com.samsolutions.logistics.mainlogistics.services.security.UserState;
import com.samsolutions.logistics.mainlogistics.services.signup.FirmsSignUpService;
import com.samsolutions.logistics.mainlogistics.services.utils.FileStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public void setFirmDTO(FirmDTO firmDTO) {
        this.firmDTO = firmDTO;
        firms = new Firms();
        passwords = new Passwords();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(firmDTO, firms);

    }

    @Override
    @Transactional
    public void saveFirm() {
        savePassword();
        save();
        saveAvatar(firmDTO.getImage());
        saveUser();
    }

    public Firms getFirms() {
        return firms;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    @Override
    public void savePassword() {
        String password = firmDTO.getPasswordRepeat();
        byte[] saltBytes = saltHash.getSalt();
        String hashPass = saltHash.get_SHA_256_SecurePassword(password, saltBytes);
        passwords.setPassHash(hashPass);
        passwords.setSalt(saltHash.getStringFromBytes(saltBytes));
        passwordsRepository.save(passwords);

    }

    @Override
    public void save() {
        firms.setPasswordId(passwords.getId());
        firms.setCreatedAt(new Date(System.currentTimeMillis()));
        firmsRepository.save(firms);

    }

    @Override
    public void saveUser() {
        Users users = new Users();
        users.setEmail(firms.getEmail());
        users.setUserState(UserState.ENABLED);
        users.setRole(Role.ROLE_FIRM_USER);
        users.setPasswordId(passwords.getId());
        usersRepository.save(users);
    }

    @Override
    public void saveAvatar(MultipartFile file) {
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM");
        String avatarPath = fileStorageService.storeFile(file,dateFormat.format(firms.getCreatedAt()),firms.getEmail());
        firms.setAvatarPath(avatarPath);
        firmsRepository.save(firms);
    }
}
