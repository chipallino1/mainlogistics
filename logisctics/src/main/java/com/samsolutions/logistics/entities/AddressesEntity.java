package com.samsolutions.logistics.entities;

import javax.persistence.*;

@Entity
@Table(name = "addresses", schema = "logistic_db", catalog = "")
public class AddressesEntity {
    private int id;
    private String streetName;
    private int streetNum;
    private String city;
    private String country;
    private FirmsEntity firmsByFirmId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "street_name", nullable = false, length = 45)
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Basic
    @Column(name = "street_num", nullable = false)
    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 45)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressesEntity that = (AddressesEntity) o;

        if (id != that.id) return false;
        if (streetNum != that.streetNum) return false;
        if (streetName != null ? !streetName.equals(that.streetName) : that.streetName != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + streetNum;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false)
    public FirmsEntity getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(FirmsEntity firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }
}
