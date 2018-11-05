package com.samsolutions.logistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "routes", schema = "logistic_db", catalog = "")
public class RoutesEntity {
    private int id;
    private String pointFrom;
    private String pointTo;
    private int cost;
    private Collection<OrdersEntity> ordersById;
    private Collection<RoutesInfoEntity> routesInfosById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "point_from", nullable = false, length = 45)
    public String getPointFrom() {
        return pointFrom;
    }

    public void setPointFrom(String pointFrom) {
        this.pointFrom = pointFrom;
    }

    @Basic
    @Column(name = "point_to", nullable = false, length = 45)
    public String getPointTo() {
        return pointTo;
    }

    public void setPointTo(String pointTo) {
        this.pointTo = pointTo;
    }

    @Basic
    @Column(name = "cost", nullable = false)
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

        if (id != that.id) return false;
        if (cost != that.cost) return false;
        if (pointFrom != null ? !pointFrom.equals(that.pointFrom) : that.pointFrom != null) return false;
        if (pointTo != null ? !pointTo.equals(that.pointTo) : that.pointTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pointFrom != null ? pointFrom.hashCode() : 0);
        result = 31 * result + (pointTo != null ? pointTo.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @OneToMany(mappedBy = "routesByRouteId")
    public Collection<OrdersEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrdersEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "routesByRouteId")
    public Collection<RoutesInfoEntity> getRoutesInfosById() {
        return routesInfosById;
    }

    public void setRoutesInfosById(Collection<RoutesInfoEntity> routesInfosById) {
        this.routesInfosById = routesInfosById;
    }
}
