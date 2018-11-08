package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "passwords", schema = "logistic_db", catalog = "")
public class PasswordsEntity {
    private Long id;
    private String passHash;
    private String salt;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pass_hash")
    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    @Basic
    @Column(name = "salt")
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
        return id == that.id &&
                Objects.equals(passHash, that.passHash) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, passHash, salt);
    }
}
