package com.samsolutions.logistics.mainlogistics.entities;

import com.samsolutions.logistics.mainlogistics.services.security.ContactState;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contacts {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNum;
    private String email;
    private Long firmId;
    private Long passwordsId;
    private ContactState contactState;
    private Firms firmsByFirmId;
    private Passwords passwordsByPasswordsId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "firm_id", nullable = true)
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

    @Basic
    @Column(name = "contact_state", length = 45)
    public ContactState getContactState() {
        return contactState;
    }

    public void setContactState(ContactState contactState) {
        this.contactState = contactState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(id, contacts.id) &&
                Objects.equals(firstName, contacts.firstName) &&
                Objects.equals(lastName, contacts.lastName) &&
                Objects.equals(phoneNum, contacts.phoneNum) &&
                Objects.equals(email, contacts.email) &&
                Objects.equals(firmId, contacts.firmId) &&
                Objects.equals(passwordsId, contacts.passwordsId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, phoneNum, email, firmId, passwordsId);
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "passwords_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Passwords getPasswordsByPasswordsId() {
        return passwordsByPasswordsId;
    }

    public void setPasswordsByPasswordsId(Passwords passwordsByPasswordsId) {
        this.passwordsByPasswordsId = passwordsByPasswordsId;
    }

}
