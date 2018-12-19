package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;

import java.util.Collection;
import java.util.Objects;

/**
 * Entity
 */
@Entity
@Table(name = "ROUTES")
public class Routes {
    private Long id;
    private String pointFrom;
    private String pointTo;
    private Collection<Orders> ordersById;
    private Collection<RoutesInfo> routesInfosById;
    private Collection<RoutesOnCarriers> routesOnCarriersByRoutesId;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routes routes = (Routes) o;
        return Objects.equals(id, routes.id) &&
                Objects.equals(pointFrom, routes.pointFrom) &&
                Objects.equals(pointTo, routes.pointTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pointFrom, pointTo);
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

    @OneToMany(mappedBy = "routesByRoutesId")
    public Collection<RoutesOnCarriers> getRoutesOnCarriersByRoutesId() {
        return routesOnCarriersByRoutesId;
    }

    public void setRoutesOnCarriersByRoutesId(Collection<RoutesOnCarriers> routesOnCarriersByRoutesId) {
        this.routesOnCarriersByRoutesId = routesOnCarriersByRoutesId;
    }
}
