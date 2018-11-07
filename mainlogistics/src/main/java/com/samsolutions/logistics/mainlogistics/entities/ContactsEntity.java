package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "contacts", schema = "logistic_db", catalog = "")
public class ContactsEntity {
    private int id;
    private String firstname;
    private String lastname;
    private String phoneNum;
    private String email;
    private int firmId;
    private int passwordsId;

    private String password;
    private String passwordRepeat;

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getPasswordRepeat() {

        return passwordRepeat;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {

        return password;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname")
    @NotNull
    @Size(min = 2,max=30)
    @Pattern(regexp = "^[a-zA-z]+$")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    @NotNull
    @Size(min = 2,max=30)
    @Pattern(regexp = "^[a-zA-z]+$")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "phone_num")
    @NotNull
    @Size(min = 7)
    @Pattern(regexp = "^\\d+$")
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "email")
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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firm_id")
    public int getFirmId() {
        return firmId;
    }

    public void setFirmId(int firmId) {
        this.firmId = firmId;
    }

    @Basic
    @Column(name = "passwords_id")
    public int getPasswordsId() {
        return passwordsId;
    }

    public void setPasswordsId(int passwordsId) {
        this.passwordsId = passwordsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactsEntity that = (ContactsEntity) o;
        return id == that.id &&
                firmId == that.firmId &&
                passwordsId == that.passwordsId &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(phoneNum, that.phoneNum) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname, phoneNum, email, firmId, passwordsId);
    }
}
