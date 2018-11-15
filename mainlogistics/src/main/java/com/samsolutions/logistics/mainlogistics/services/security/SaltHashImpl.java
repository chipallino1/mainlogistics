package com.samsolutions.logistics.mainlogistics.services.security;

import com.samsolutions.logistics.mainlogistics.repositories.PasswordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class SaltHashImpl implements SaltHash {

    private PasswordsRepository passwordsRepository;
    @Autowired
    public void setPasswordsRepository(PasswordsRepository passwordsRepository) {
        this.passwordsRepository = passwordsRepository;
    }

    @Override
    public String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());

            generatedPassword = getStringFromBytes(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    @Override
    public byte[] getSalt()
    {
        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    @Override
    public String getSaltByHash(String hash) {
        return passwordsRepository.findDistinctTop1ByPassHashLike(hash).get(0).getSalt();
    }

    @Override
    public String getStringFromBytes(byte[] bytes){

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
    @Override
    public byte[] getBytesFromString(String string) {

       byte[] byteArr= new BigInteger(string, 16).toByteArray();
       return byteArr; // Arrays.copyOfRange(byteArr,1,byteArr.length);



    }
    @Override
    public boolean validate(String passwordToValidate,String storedSecurePassword,byte[] storedSalt){

        if(storedSecurePassword.equals(get_SHA_256_SecurePassword(passwordToValidate,storedSalt))){
            return true;
        }
        return false;

    }


    @Override
    public String encode(CharSequence charSequence) {
        return get_SHA_256_SecurePassword(charSequence.toString(),getSalt());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return validate(charSequence.toString(),s,getBytesFromString(getSaltByHash(s)));
    }
}
