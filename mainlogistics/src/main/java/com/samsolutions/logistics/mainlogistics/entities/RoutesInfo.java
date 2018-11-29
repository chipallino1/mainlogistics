package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;

/**
 * Entity
 */
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return Objects.equals(id, that.id) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateFinish, that.dateFinish) &&
                Objects.equals(optimality, that.optimality) &&
                Objects.equals(length, that.length) &&
                Objects.equals(routeId, that.routeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateStart, dateFinish, optimality, length, routeId);
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
