package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        return cost == routes.cost &&
                Objects.equals(id, routes.id) &&
                Objects.equals(pointFrom, routes.pointFrom) &&
                Objects.equals(pointTo, routes.pointTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pointFrom, pointTo, cost);
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
