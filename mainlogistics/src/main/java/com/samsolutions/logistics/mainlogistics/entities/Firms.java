package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Firms {
    private Long id;
    private String firmName;
    private Double rating;
    private String firmType;
    private String description;
    private String email;
    private Long passwordId;
    private Collection<Addresses> addressesById;
    private Collection<Contacts> contactsById;
    private Passwords passwordsByPasswordId;
    private Collection<Orders> ordersById;
    private Collection<Orders> ordersById_0;
    private Collection<Users> usersById;

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

    @Basic
    @Column(name = "password_id", nullable = false)
    public Long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Long passwordId) {
        this.passwordId = passwordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firms firms = (Firms) o;
        return Objects.equals(id, firms.id) &&
                Objects.equals(firmName, firms.firmName) &&
                Objects.equals(rating, firms.rating) &&
                Objects.equals(firmType, firms.firmType) &&
                Objects.equals(description, firms.description) &&
                Objects.equals(email, firms.email) &&
                Objects.equals(passwordId, firms.passwordId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firmName, rating, firmType, description, email, passwordId);
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

    @ManyToOne
    @JoinColumn(name = "password_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Passwords getPasswordsByPasswordId() {
        return passwordsByPasswordId;
    }

    public void setPasswordsByPasswordId(Passwords passwordsByPasswordId) {
        this.passwordsByPasswordId = passwordsByPasswordId;
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


    @OneToMany(mappedBy = "firmsByFirmId")
    public Collection<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<Users> usersById) {
        this.usersById = usersById;
    }
}
