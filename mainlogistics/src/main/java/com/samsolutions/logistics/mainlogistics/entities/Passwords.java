package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Passwords {
    private Long id;
    private String passHash;
    private String salt;
    private Collection<Contacts> contactsById;
    private Collection<Firms> firmsById;

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
    @Column(name = "pass_hash", nullable = true, length = 300)
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 300)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passwords passwords = (Passwords) o;
        return Objects.equals(id, passwords.id) &&
                Objects.equals(passHash, passwords.passHash) &&
                Objects.equals(salt, passwords.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, passHash, salt);
    }

    @OneToMany(mappedBy = "passwordsByPasswordsId")
    public Collection<Contacts> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contacts> contactsById) {
        this.contactsById = contactsById;
    }

    @OneToMany(mappedBy = "passwordsByPasswordId")
    public Collection<Firms> getFirmsById() {
        return firmsById;
    }

    public void setFirmsById(Collection<Firms> firmsById) {
        this.firmsById = firmsById;
    }
}
