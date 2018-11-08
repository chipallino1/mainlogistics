package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Firms {
    private Long id;
    private String firmName;
    private Double rating;
    private String firmType;
    private String description;
    private String email;
    private Collection<Addresses> addressesById;
    private Collection<Contacts> contactsById;
    private Collection<Orders> ordersById;
    private Collection<Orders> ordersById_0;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firm_name", nullable = false, length = 45)
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    @Basic
    @Column(name = "rating", nullable = true, precision = 0)
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "firm_type", nullable = true, length = 45)
    public String getFirmType() {
        return firmType;
    }

    public void setFirmType(String firmType) {
        this.firmType = firmType;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Firms firms = (Firms) o;

        if (id != null ? !id.equals(firms.id) : firms.id != null) return false;
        if (firmName != null ? !firmName.equals(firms.firmName) : firms.firmName != null) return false;
        if (rating != null ? !rating.equals(firms.rating) : firms.rating != null) return false;
        if (firmType != null ? !firmType.equals(firms.firmType) : firms.firmType != null) return false;
        if (description != null ? !description.equals(firms.description) : firms.description != null) return false;
        if (email != null ? !email.equals(firms.email) : firms.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firmName != null ? firmName.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (firmType != null ? firmType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "firmsByFirmId")
    public Collection<Addresses> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<Addresses> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "firmsByFirmId")
    public Collection<Contacts> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<Contacts> contactsById) {
        this.contactsById = contactsById;
    }

    @OneToMany(mappedBy = "firmsByProducerId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "firmsByConsumerId")
    public Collection<Orders> getOrdersById_0() {
        return ordersById_0;
    }

    public void setOrdersById_0(Collection<Orders> ordersById_0) {
        this.ordersById_0 = ordersById_0;
    }
}
