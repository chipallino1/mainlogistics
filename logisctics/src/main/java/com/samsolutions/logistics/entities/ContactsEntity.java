package com.samsolutions.logistics.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts", schema = "logistic_db", catalog = "")
public class ContactsEntity {
    private int id;
    private String firstname;
    private String lastname;
    private String phoneNum;
    private String email;
    private FirmsEntity firmsByFirmId;
    private PasswordsEntity passwordsByPasswordsId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 45)
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
    @Column(name = "lastname", nullable = false, length = 45)
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
    @Column(name = "phone_num", nullable = false, length = 45)
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
    @Column(name = "email", nullable = true, length = 45)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactsEntity that = (ContactsEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false)
    public FirmsEntity getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(FirmsEntity firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "passwords_id", referencedColumnName = "id", nullable = false)
    public PasswordsEntity getPasswordsByPasswordsId() {
        return passwordsByPasswordsId;
    }

    public void setPasswordsByPasswordsId(PasswordsEntity passwordsByPasswordsId) {
        this.passwordsByPasswordsId = passwordsByPasswordsId;
    }
}
