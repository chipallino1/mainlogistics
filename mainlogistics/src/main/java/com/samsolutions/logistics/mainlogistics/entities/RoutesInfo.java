package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "routes_info", schema = "logistic_db", catalog = "")
public class RoutesInfo {
    private Long id;
    private Date dateStart;
    private Date dateFinish;
    private String optimality;
    private Long length;
    private Long routeId;
    private Routes routesByRouteId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Basic
    @Column(name = "route_id", nullable = false)
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoutesInfo that = (RoutesInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateStart != null ? !dateStart.equals(that.dateStart) : that.dateStart != null) return false;
        if (dateFinish != null ? !dateFinish.equals(that.dateFinish) : that.dateFinish != null) return false;
        if (optimality != null ? !optimality.equals(that.optimality) : that.optimality != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (routeId != null ? !routeId.equals(that.routeId) : that.routeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateStart != null ? dateStart.hashCode() : 0);
        result = 31 * result + (dateFinish != null ? dateFinish.hashCode() : 0);
        result = 31 * result + (optimality != null ? optimality.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Routes getRoutesByRouteId() {
        return routesByRouteId;
    }

    public void setRoutesByRouteId(Routes routesByRouteId) {
        this.routesByRouteId = routesByRouteId;
    }
}
