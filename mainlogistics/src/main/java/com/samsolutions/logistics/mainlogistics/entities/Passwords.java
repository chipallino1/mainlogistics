package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Passwords {
    private Long id;
    private String passHash;
    private String salt;
    private Collection<Contacts> contactsById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        if (id != null ? !id.equals(passwords.id) : passwords.id != null) return false;
        if (passHash != null ? !passHash.equals(passwords.passHash) : passwords.passHash != null) return false;
        if (salt != null ? !salt.equals(passwords.salt) : passwords.salt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (passHash != null ? passHash.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "passwordsByPasswordsId")
    public Collection<Contacts> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contacts> contactsById) {
        this.contactsById = contactsById;
    }
}
