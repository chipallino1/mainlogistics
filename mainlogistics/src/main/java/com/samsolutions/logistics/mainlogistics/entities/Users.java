package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;

@Entity
public class Users {
    private Long id;
    private String enabled;
    private String role;
    private Long contactId;
    private Long firmId;
    private Contacts contactsByContactId;
    private Firms firmsByFirmId;

    @Id
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
    @Column(name = "role", nullable = false, length = 55)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    @Column(name = "firm_id", nullable = true)
    public Long getFirmId() {
        return firmId;
    }

    public void setFirmId(Long firmId) {
        this.firmId = firmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != null ? !id.equals(users.id) : users.id != null) return false;
        if (enabled != null ? !enabled.equals(users.enabled) : users.enabled != null) return false;
        if (role != null ? !role.equals(users.role) : users.role != null) return false;
        if (contactId != null ? !contactId.equals(users.contactId) : users.contactId != null) return false;
        if (firmId != null ? !firmId.equals(users.firmId) : users.firmId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (contactId != null ? contactId.hashCode() : 0);
        result = 31 * result + (firmId != null ? firmId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Contacts getContactsByContactId() {
        return contactsByContactId;
    }

    public void setContactsByContactId(Contacts contactsByContactId) {
        this.contactsByContactId = contactsByContactId;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", insertable = false, updatable = false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }
}
