package com.samsolutions.logistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "firms", schema = "logistic_db", catalog = "")
public class FirmsEntity {
    private int id;
    private String firmName;
    private Double rating;
    private String firmType;
    private Collection<AddressesEntity> addressesById;
    private Collection<ContactsEntity> contactsById;
    private Collection<OrdersEntity> ordersById;
    private Collection<OrdersEntity> ordersById_0;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirmsEntity that = (FirmsEntity) o;

        if (id != that.id) return false;
        if (firmName != null ? !firmName.equals(that.firmName) : that.firmName != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;
        if (firmType != null ? !firmType.equals(that.firmType) : that.firmType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firmName != null ? firmName.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (firmType != null ? firmType.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "firmsByFirmId")
    public Collection<AddressesEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressesEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "firmsByFirmId")
    public Collection<ContactsEntity> getContactsById() {
        return contactsById;
    }

    public void setContactsById(Collection<ContactsEntity> contactsById) {
        this.contactsById = contactsById;
    }

    @OneToMany(mappedBy = "firmsByProducerId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "firmsByConsumerId")
    public Collection<OrdersEntity> getOrdersById_0() {
        return ordersById_0;
    }

    public void setOrdersById_0(Collection<OrdersEntity> ordersById_0) {
        this.ordersById_0 = ordersById_0;
    }
}
