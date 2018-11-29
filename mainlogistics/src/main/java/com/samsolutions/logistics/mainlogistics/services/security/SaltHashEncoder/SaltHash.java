package com.samsolutions.logistics.mainlogistics.services.security.SaltHashEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Custom impl of password encoder
 */
@Component
public interface SaltHash extends PasswordEncoder {
    /**
     * Encode password
     * @param passwordToHash string that will be encoded
     * @param salt bytes that secure hash
     * @return encoded string
     */
    String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt);

    /**
     * Get salt
     * @return bytes that secure hash
     */
    byte[] getSalt();

    /**
     * Get salt by hash
     * @param hash hash that helps searching
     * @return salt
     */
    String getSaltByHash(String hash);

    /**
     * Get string from bytes
     * @param bytes bytes that secure hash
     * @return string representation of bytes
     */
    String getStringFromBytes(byte[] bytes);

    /**
     * Get bytes from string
     * @param string string representation of bytes that secure hash
     * @return bytes that secure hash
     * @throws UnsupportedEncodingException
     */
    byte[] getBytesFromString(String string) throws UnsupportedEncodingException;

    /**
     * Check password
     * @param passwordToValidate password
     * @param storedSecurePassword hash in db
     * @param storedSalt salt in db
     * @return true if validation was success else false
     */
    boolean validate(String passwordToValidate, String storedSecurePassword, byte[] storedSalt);

}
