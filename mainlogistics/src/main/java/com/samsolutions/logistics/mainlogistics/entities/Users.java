package com.samsolutions.logistics.mainlogistics.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    private Long id;
    private String enabled;
    private String role;
    private String email;
    private Passwords passwordsByPasswordId;
    private Long passwordId;

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
    @Column(name = "enabled", nullable = false, length = 10)
    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
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
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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

    @ManyToOne
    @JoinColumn(name = "password_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
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

                Objects.equals(enabled, users.enabled) &&

                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, enabled, role);
    }

}
