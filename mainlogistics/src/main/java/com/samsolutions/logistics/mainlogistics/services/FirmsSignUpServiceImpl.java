package com.samsolutions.logistics.mainlogistics.services;

import com.samsolutions.logistics.mainlogistics.dto.FirmDTO;
import com.samsolutions.logistics.mainlogistics.entities.Firms;
import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.entities.Users;
import com.samsolutions.logistics.mainlogistics.repositories.FirmsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.repositories.UsersRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHash;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirmsSignUpServiceImpl implements FirmsSignUpService {

    private FirmsRepository firmsRepository;
    private PasswordsRepository passwordsRepository;
    private SaltHash saltHash;
    private FirmDTO firmDTO;
    private Firms firms;
    private Passwords passwords;
    private Users users;
    private UsersRepository usersRepository;

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

    @Override
    public void setFirmDTO(FirmDTO firmDTO) {
        this.firmDTO = firmDTO;
        firms=new Firms();
        passwords=new Passwords();
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(firmDTO,firms);

    }
    public Firms getFirms(){
        return firms;
    }

    public Passwords getPasswords() {
        return passwords;
    }

    @Override
    public void savePassword() {

        String password = firmDTO.getPasswordRepeat();
        byte[] saltBytes = saltHash.getSalt();
        String hashPass = saltHash.get_SHA_256_SecurePassword(password,saltBytes);
        passwords.setPassHash(hashPass);
        passwords.setSalt(saltHash.getStringFromBytes(saltBytes));
        passwordsRepository.save(passwords);

    }

    @Override
    public void save(){

        firms.setPasswordId(passwords.getId());
        firmsRepository.save(firms);

    }

    @Override
    public void saveUser() {
        users = new Users();
        users.setEmail(firms.getEmail());
        users.setEnabled("true");
        users.setRole("ROLE_FIRM_USER");
        users.setPasswordId(passwords.getId());
        usersRepository.save(users);
    }
}
