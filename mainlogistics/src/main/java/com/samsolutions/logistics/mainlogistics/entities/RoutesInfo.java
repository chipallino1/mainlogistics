package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Entity
 */
//@Entity
@Table(name = "routes_info", schema = "logistic_db", catalog = "")
public class RoutesInfo {
    private Long id;
    private Date dateStart;
    private Date dateFinish;
    private Long length;
    private Long duration;
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
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_finish", nullable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
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
    @Column(name = "duration", nullable = false)
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
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
                Objects.equals(length, that.length) &&
                Objects.equals(routeId, that.routeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateStart, dateFinish, length, routeId);
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
