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
    private Long pointFromId;
    private Long pointToId;
    private Points pointsByPointFromId;
    private Points pointsByPointToId;
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
    @Column(name = "point_from_id")
    public Long getPointFromId() {
        return pointFromId;
    }

    public void setPointFromId(Long pointFromId) {
        this.pointFromId = pointFromId;
    }

    @Basic
    @Column(name = "point_to_id")
    public Long getPointToId() {
        return pointToId;
    }

    public void setPointToId(Long pointToId) {
        this.pointToId = pointToId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routes routes = (Routes) o;
        return Objects.equals(id, routes.id) &&
                Objects.equals(pointFromId, routes.pointFromId) &&
                Objects.equals(pointToId, routes.pointToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pointFromId, pointToId);
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

    @ManyToOne
    @JoinColumn(name = "point_from_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Points getPointsByPointFromId() {
        return pointsByPointFromId;
    }

    public void setPointsByPointFromId(Points pointsByPointFromId) {
        this.pointsByPointFromId = pointsByPointFromId;
    }

    @ManyToOne
    @JoinColumn(name = "point_to_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Points getPointsByPointToId() {
        return pointsByPointToId;
    }

    public void setPointsByPointToId(Points pointsByPointToId) {
        this.pointsByPointToId = pointsByPointToId;
    }
}
