package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Routes {
    private Long id;
    private String pointFrom;
    private String pointTo;
    private int cost;
    private Collection<Orders> ordersById;
    private Collection<RoutesInfo> routesInfosById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        Routes routes = (Routes) o;

        if (cost != routes.cost) return false;
        if (id != null ? !id.equals(routes.id) : routes.id != null) return false;
        if (pointFrom != null ? !pointFrom.equals(routes.pointFrom) : routes.pointFrom != null) return false;
        if (pointTo != null ? !pointTo.equals(routes.pointTo) : routes.pointTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pointFrom != null ? pointFrom.hashCode() : 0);
        result = 31 * result + (pointTo != null ? pointTo.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @OneToMany(mappedBy = "routesByRouteId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }

    @OneToMany(mappedBy = "routesByRouteId")
    public Collection<RoutesInfo> getRoutesInfosById() {
        return routesInfosById;
    }

    public void setRoutesInfosById(Collection<RoutesInfo> routesInfosById) {
        this.routesInfosById = routesInfosById;
    }
}
