package com.samsolutions.logistics.mainlogistics.entities;

import com.samsolutions.logistics.mainlogistics.services.security.Role;
import com.samsolutions.logistics.mainlogistics.services.security.UserState;

import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.Objects;

/**
 * Entity
 */
@Entity
public class Users {
    private Long id;
    private Role role;
    private String email;
    private Passwords passwordsByPasswordId;
    private Long passwordId;
    private UserState userState;

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
    @Column(name = "password_id")
    public Long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Long passwordId) {
        this.passwordId = passwordId;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 55)
    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_state")
    @Enumerated(value = EnumType.ORDINAL)
    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }

    @ManyToOne
    @JoinColumn(name = "password_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Passwords getPasswordsByPasswordId() {
        return passwordsByPasswordId;
    }

    public void setPasswordsByPasswordId(Passwords passwordsByPasswordId) {
        this.passwordsByPasswordId = passwordsByPasswordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&

                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, role);
    }

}
