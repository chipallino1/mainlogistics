package com.samsolutions.logistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "passwords", schema = "logistic_db", catalog = "")
public class PasswordsEntity {
    private int id;
    private String passHash;
    private String salt;
    private Collection<ContactsEntity> contactsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        PasswordsEntity that = (PasswordsEntity) o;

        if (id != that.id) return false;
        if (passHash != null ? !passHash.equals(that.passHash) : that.passHash != null) return false;
        if (salt != null ? !salt.equals(that.salt) : that.salt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (passHash != null ? passHash.hashCode() : 0);
        result = 31 * result + (salt != null ? salt.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "passwordsByPasswordsId")
    public Collection<ContactsEntity> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<ContactsEntity> contactsById) {
        this.contactsById = contactsById;
    }
}
