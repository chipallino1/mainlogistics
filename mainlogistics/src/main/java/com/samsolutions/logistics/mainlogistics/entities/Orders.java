package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

/**
 * Entity
 */
@Entity
public class Orders {
    private Long id;
    private Long yourCapacity;
    private Long yourVolume;
    private Long routesOnCarriersId;
    private Date orderDate;
    private Long producerId;
    private Long consumerFirmId;
    private Long consumerContactId;
    private RoutesOnCarriers routesOnCarriersByRoutesOnCarriersId;
    private Firms firmsByProducerId;
    private Firms firmsByConsumerFirmId;
    private Contacts contactsByConsumerContactId;

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
    @Column(name = "your_capacity")
    public Long getYourCapacity() {
        return yourCapacity;
    }

    public void setYourCapacity(Long yourCapacity) {
        this.yourCapacity = yourCapacity;
    }
    @Basic
    @Column(name = "your_volume")
    public Long getYourVolume() {
        return yourVolume;
    }

    public void setYourVolume(Long yourVolume) {
        this.yourVolume = yourVolume;
    }

    @Basic
    @Column(name = "routes_on_carriers_id", nullable = false)
    public Long getRoutesOnCarriersId() {
        return routesOnCarriersId;
    }

    public void setRoutesOnCarriersId(Long routesOnCarriersId) {
        this.routesOnCarriersId = routesOnCarriersId;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "producer_id", nullable = false)
    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    @Basic
    @Column(name = "consumer_firm_id")
    public Long getConsumerFirmId() {
        return consumerFirmId;
    }

    public void setConsumerFirmId(Long consumerFirmId) {
        this.consumerFirmId = consumerFirmId;
    }

    @Basic
    @Column(name = "consumer_contact_id")
    public Long getConsumerContactId() {
        return consumerContactId;
    }

    public void setConsumerContactId(Long consumerContactId) {
        this.consumerContactId = consumerContactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(producerId, orders.producerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderDate, producerId);
    }

    @ManyToOne
    @JoinColumn(name = "routes_on_carriers_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public RoutesOnCarriers getRoutesOnCarriersByRoutesOnCarriersId() {
        return routesOnCarriersByRoutesOnCarriersId;
    }

    public void setRoutesOnCarriersByRoutesOnCarriersId(RoutesOnCarriers routesOnCarriersByRoutesOnCarriersId) {
        this.routesOnCarriersByRoutesOnCarriersId = routesOnCarriersByRoutesOnCarriersId;
    }

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Firms getFirmsByProducerId() {
        return firmsByProducerId;
    }

    public void setFirmsByProducerId(Firms firmsByProducerId) {
        this.firmsByProducerId = firmsByProducerId;
    }

    @ManyToOne
    @JoinColumn(name = "consumer_firm_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Firms getFirmsByConsumerFirmId() {
        return firmsByConsumerFirmId;
    }

    public void setFirmsByConsumerFirmId(Firms firmsByConsumerFirmId) {
        this.firmsByConsumerFirmId = firmsByConsumerFirmId;
    }

    @ManyToOne
    @JoinColumn(name = "consumer_contact_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Contacts getContactsByConsumerContactId() {
        return contactsByConsumerContactId;
    }

    public void setContactsByConsumerContactId(Contacts contactsByConsumerContactId) {
        this.contactsByConsumerContactId = contactsByConsumerContactId;
    }
}
