package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    private Long id;
    private Long contactId;
    private String enabled;
    private Long firmId;
    private String role;
    private Firms firmsByFirmId;
    private Contacts contactsByContactId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contact_id", nullable = true)
    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
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
    @Column(name = "firm_id", nullable = true)
    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 55)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Contacts getContactsByContactId() {
        return contactsByContactId;
    }

    public void setContactsByContactId(Contacts contactsByContactId) {
        this.contactsByContactId = contactsByContactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(id, users.id) &&
                Objects.equals(contactId, users.contactId) &&
                Objects.equals(enabled, users.enabled) &&
                Objects.equals(firmId, users.firmId) &&
                Objects.equals(role, users.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, contactId, enabled, firmId, role);
    }

}
