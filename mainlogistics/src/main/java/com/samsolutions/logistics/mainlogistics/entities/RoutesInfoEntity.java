package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "routes_info", schema = "logistic_db", catalog = "")
public class RoutesInfoEntity {
    private Long id;
    private Date dateStart;
    private Date dateFinish;
    private String optimality;
    private int length;
    private int routeId;

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
    @Column(name = "date_start")
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "date_finish")
    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    @Basic
    @Column(name = "optimality")
    public String getOptimality() {
        return optimality;
    }

    public void setOptimality(String optimality) {
        this.optimality = optimality;
    }

    @Basic
    @Column(name = "length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "route_id")
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutesInfoEntity that = (RoutesInfoEntity) o;
        return id == that.id &&
                length == that.length &&
                routeId == that.routeId &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateFinish, that.dateFinish) &&
                Objects.equals(optimality, that.optimality);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dateStart, dateFinish, optimality, length, routeId);
    }
}
