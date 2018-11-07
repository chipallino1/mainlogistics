package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "firms", schema = "logistic_db", catalog = "")
public class FirmsEntity {
    private int id;
    private String firmName;
    private String email;
    private String description;
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
    @NotNull
    @Size(min = 2,max=30)
    @Pattern(regexp = "^[a-zA-z]+$")
    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    @Basic
    @Column(name = "email",nullable = false)
    @NotNull
    @Email(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
            "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x2" +
            "1\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x" +
            "09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z" +
            "0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?" +
            ":[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|" +
            "[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.)" +
            "{3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|" +
            "[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-" +
            "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53" +
            "-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\" +
            "x7f])+)\\])")
    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    @Basic
    @Column(name = "description")
    public String getDescription(){
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
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
    @NotNull

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
