package com.samsolutions.logistics.mainlogistics.services.security;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public interface SaltHash {

    String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt);
    byte[] getSalt();
    String getStringFromBytes(byte[] bytes);
    byte[] getBytesFromString(String string) throws UnsupportedEncodingException;
    boolean validate(String passwordToValidate,String storedSecurePassword,byte[] storedSalt);

}
