package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;

@Entity
public class Addresses {
    private Long id;
    private String streetName;
    private int streetNum;
    private String city;
    private String country;
    private Long firmId;
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

    @Basic
    @Column(name = "firm_id", nullable = false,insertable=false, updatable=false)
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

        Addresses addresses = (Addresses) o;

        if (streetNum != addresses.streetNum) return false;
        if (id != null ? !id.equals(addresses.id) : addresses.id != null) return false;
        if (streetName != null ? !streetName.equals(addresses.streetName) : addresses.streetName != null) return false;
        if (city != null ? !city.equals(addresses.city) : addresses.city != null) return false;
        if (country != null ? !country.equals(addresses.country) : addresses.country != null) return false;
        if (firmId != null ? !firmId.equals(addresses.firmId) : addresses.firmId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);
        result = 31 * result + streetNum;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (firmId != null ? firmId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }
}
