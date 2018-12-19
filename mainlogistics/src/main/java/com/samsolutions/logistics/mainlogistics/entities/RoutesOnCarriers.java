package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "routes_on_carriers")
public class RoutesOnCarriers {

    private Long id;
    private Long routesId;
    private Long carriersId;
    private Routes routesByRoutesId;
    private Carriers carriersByCarriersId;

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
    @Column(name = "routes_id")
    public Long getRoutesId() {
        return routesId;
    }

    public void setRoutesId(Long routesId) {
        this.routesId = routesId;
    }

    @Basic
    @Column(name = "carriers_id")
    public Long getCarriersId() {
        return carriersId;
    }

    public void setCarriersId(Long carriersId) {
        this.carriersId = carriersId;
    }

    @ManyToOne
    @JoinColumn(name = "routes_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Routes getRoutesByRoutesId() {
        return routesByRoutesId;
    }

    public void setRoutesByRoutesId(Routes routesByRoutesId) {
        this.routesByRoutesId = routesByRoutesId;
    }

    @ManyToOne
    @JoinColumn(name = "carriers_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Carriers getCarriersByCarriersId() {
        return carriersByCarriersId;
    }

    public void setCarriersByCarriersId(Carriers carriersByCarriersId) {
        this.carriersByCarriersId = carriersByCarriersId;
    }
}
