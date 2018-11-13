package com.samsolutions.logistics.mainlogistics.services.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public interface SaltHash extends PasswordEncoder {

    String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt);
    byte[] getSalt();
    String getSaltByHash(String hash);
    String getStringFromBytes(byte[] bytes);
    byte[] getBytesFromString(String string) throws UnsupportedEncodingException;
    boolean validate(String passwordToValidate,String storedSecurePassword,byte[] storedSalt);

}
