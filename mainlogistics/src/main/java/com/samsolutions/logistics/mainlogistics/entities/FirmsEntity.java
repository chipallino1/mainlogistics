package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "firms", schema = "logistic_db", catalog = "")
public class FirmsEntity {
    private int id;
    private String firmName;
    private Double rating;
    private String firmType;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firm_name")
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "firm_type")
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
        return id == that.id &&
                Objects.equals(firmName, that.firmName) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(firmType, that.firmType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firmName, rating, firmType);
    }
}
