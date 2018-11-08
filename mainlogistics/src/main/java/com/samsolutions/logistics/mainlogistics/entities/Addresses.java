package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "firm_id", nullable = false)
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
        return streetNum == addresses.streetNum &&
                Objects.equals(id, addresses.id) &&
                Objects.equals(streetName, addresses.streetName) &&
                Objects.equals(city, addresses.city) &&
                Objects.equals(country, addresses.country) &&
                Objects.equals(firmId, addresses.firmId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, streetName, streetNum, city, country, firmId);
    }

    @ManyToOne
    @JoinColumn(name = "firm_id", referencedColumnName = "id", nullable = false, insertable=false, updatable=false)
    public Firms getFirmsByFirmId() {
        return firmsByFirmId;
    }

    public void setFirmsByFirmId(Firms firmsByFirmId) {
        this.firmsByFirmId = firmsByFirmId;
    }
}
