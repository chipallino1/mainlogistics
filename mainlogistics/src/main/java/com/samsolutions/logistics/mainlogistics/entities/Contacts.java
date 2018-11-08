package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;

@Entity
public class Contacts {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private Long firmId;
    private Long passwordsId;
    private Firms firmsByFirmId;
    private Passwords passwordsByPasswordsId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "phone_num", nullable = false, length = 45)
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firm_id", nullable = false)
    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    @Basic
    @Column(name = "passwords_id", nullable = false)
    public Long getPasswordsId() {
        return passwordsId;
    }

    public void setPasswordsId(Long passwordsId) {
        this.passwordsId = passwordsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacts contacts = (Contacts) o;

        if (id != null ? !id.equals(contacts.id) : contacts.id != null) return false;
        if (firstName != null ? !firstName.equals(contacts.firstName) : contacts.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contacts.lastName) : contacts.lastName != null) return false;
        if (phoneNum != null ? !phoneNum.equals(contacts.phoneNum) : contacts.phoneNum != null) return false;
        if (email != null ? !email.equals(contacts.email) : contacts.email != null) return false;
        if (firmId != null ? !firmId.equals(contacts.firmId) : contacts.firmId != null) return false;
        if (passwordsId != null ? !passwordsId.equals(contacts.passwordsId) : contacts.passwordsId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firmId != null ? firmId.hashCode() : 0);
        result = 31 * result + (passwordsId != null ? passwordsId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "passwords_id", referencedColumnName = "id", nullable = false)
    public Passwords getPasswordsByPasswordsId() {
        return passwordsByPasswordsId;
    }

    public void setPasswordsByPasswordsId(Passwords passwordsByPasswordsId) {
        this.passwordsByPasswordsId = passwordsByPasswordsId;
    }
}
