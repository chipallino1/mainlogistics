package com.samsolutions.logistics.mainlogistics.services.signup;

import com.samsolutions.logistics.mainlogistics.entities.Passwords;
import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder.SaltHash;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;

/**
 * Sign up service
 */
@Component
public interface SignUpService {
    /**
     * Save Passwords entity
     */
    default void savePassword(SaltHash saltHash, PasswordsRepository passwordsRepository, Passwords passwords, String password){
        byte[] saltBytes = saltHash.getSalt();
        String hashPass = saltHash.get_SHA_256_SecurePassword(password, saltBytes);
        passwords.setPassHash(hashPass);
        passwords.setSalt(saltHash.getStringFromBytes(saltBytes));
        passwordsRepository.save(passwords);
    }

    /**
     * Save Contacts entities
     */
    void save();

    /**
     * Save Users entity
     */
    void saveUser();

    /**
     * Save avatar(image)
     * @param file
     */
    void saveAvatar(MultipartFile file);

    /**
     * Save avatar(image)
     * @param file
     */
    void saveAvatar(Part file);

}
