package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Orders {
    private Long id;
    private Long routeId;
    private Date orderDate;
    private Date paymentDay;
    private Long producerId;
    private Long consumerId;
    private Routes routesByRouteId;
    private Firms firmsByProducerId;
    private Firms firmsByConsumerId;

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
    @Column(name = "route_id", nullable = false)
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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
    @Column(name = "payment_day", nullable = false)
    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
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
    @Column(name = "consumer_id", nullable = false)
    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(routeId, orders.routeId) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(paymentDay, orders.paymentDay) &&
                Objects.equals(producerId, orders.producerId) &&
                Objects.equals(consumerId, orders.consumerId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, routeId, orderDate, paymentDay, producerId, consumerId);
    }

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Routes getRoutesByRouteId() {
        return routesByRouteId;
    }

    public void setRoutesByRouteId(Routes routesByRouteId) {
        this.routesByRouteId = routesByRouteId;
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
    @JoinColumn(name = "consumer_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Firms getFirmsByConsumerId() {
        return firmsByConsumerId;
    }

    public void setFirmsByConsumerId(Firms firmsByConsumerId) {
        this.firmsByConsumerId = firmsByConsumerId;
    }
}
