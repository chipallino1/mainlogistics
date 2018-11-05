package com.samsolutions.logistics.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "routes_info", schema = "logistic_db", catalog = "")
public class RoutesInfoEntity {
    private int id;
    private Date dateStart;
    private Date dateFinish;
    private String optimality;
    private int length;
    private RoutesEntity routesByRouteId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_start", nullable = false)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_finish", nullable = false)
    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Basic
    @Column(name = "optimality", nullable = true, length = 45)
    public String getOptimality() {
        return optimality;
    }

    public void setOptimality(String optimality) {
        this.optimality = optimality;
    }

    @Basic
    @Column(name = "length", nullable = false)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesInfoEntity that = (RoutesInfoEntity) o;

        if (id != that.id) return false;
        if (length != that.length) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateFinish != null ? !dateFinish.equals(that.dateFinish) : that.dateFinish != null) return false;
        if (optimality != null ? !optimality.equals(that.optimality) : that.optimality != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (optimality != null ? optimality.hashCode() : 0);
        result = 31 * result + length;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false)
    public RoutesEntity getRoutesByRouteId() {
        return routesByRouteId;
    }

    public void setRoutesByRouteId(RoutesEntity routesByRouteId) {
        this.routesByRouteId = routesByRouteId;
    }
}
