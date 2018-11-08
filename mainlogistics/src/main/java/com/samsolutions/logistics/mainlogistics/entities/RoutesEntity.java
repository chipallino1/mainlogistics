package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "routes", schema = "logistic_db", catalog = "")
public class RoutesEntity {
    private Long id;
    private String pointFrom;
    private String pointTo;
    private int cost;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "point_from")
    public String getPointFrom() {
        return pointFrom;
    }

    public void setPointFrom(String pointFrom) {
        this.pointFrom = pointFrom;
    }

    @Basic
    @Column(name = "point_to")
    public String getPointTo() {
        return pointTo;
    }

    public void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutesEntity that = (RoutesEntity) o;
        return id == that.id &&
                cost == that.cost &&
                Objects.equals(pointFrom, that.pointFrom) &&
                Objects.equals(pointTo, that.pointTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pointFrom, pointTo, cost);
    }
}
