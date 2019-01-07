package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="CARRIERS")
public class Carriers {

    private Long id;
    private String carName;
    private String carrierName;
    private Long volume;
    private Long capacity;
    private Long cost;
    private Collection<RoutesOnCarriers> routesOnCarriersByCarriersId;

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
    @Column(name = "car_name")
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Basic
    @Column(name = "carrier_name")
    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    @Basic
    @Column(name = "volume")
    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "capacity")
    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "cost")
    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "carriersByCarriersId")
    public Collection<RoutesOnCarriers> getRoutesOnCarriersByCarriersId() {
        return routesOnCarriersByCarriersId;
    }

    public void setRoutesOnCarriersByCarriersId(Collection<RoutesOnCarriers> routesOnCarriersByCarriersId) {
        this.routesOnCarriersByCarriersId = routesOnCarriersByCarriersId;
    }
}
