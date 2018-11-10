package com.samsolutions.logistics.mainlogistics.dto;

import com.samsolutions.logistics.mainlogistics.validation.PasswordConfirm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@PasswordConfirm(password = "password",confirmPassword = "passwordRepeat")
public class ContactDTO implements Serializable {

    @NotNull
    @Size(min = 2,max=30)
    @Pattern(regexp = "^[a-zA-z]+$")
    private String firstName;

    @NotNull
    @Size(min = 2,max=30)
    @Pattern(regexp = "^[a-zA-z]+$")
    private String lastName;

    @NotNull
    @Size(min = 7)
    @Pattern(regexp = "^\\d+$")
    private String phoneNum;

    @NotNull
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x2" +
            "1\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x" +
            "09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z" +
            "0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?" +
            ":[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|" +
            "[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.)" +
            "{3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|" +
            "[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-" +
            "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53" +
            "-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\" +
            "x7f])+)\\])")
    private String email;
    private String firmName;
    private String password;
    private String passwordRepeat;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getPasswordRepeat() {

        return passwordRepeat;
    }

    public String getPassword() {

        return password;
    }
}
