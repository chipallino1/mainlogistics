package com.samsolutions.logistics.mainlogistics.dto;

import com.samsolutions.logistics.mainlogistics.validation.PasswordConfirm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PasswordConfirm(password = "password", confirmPassword = "passwordRepeat")
public class FirmDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 30)
    @Pattern(regexp = "^[a-zA-z]+$")
    private String firmName;

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

    private String description;
    private Double rating;

    @NotNull
    private String firmType;

    private String password;
    private String passwordRepeat;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getFirmType() {
        return firmType;
    }

    public void setFirmType(String firmType) {
        this.firmType = firmType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
