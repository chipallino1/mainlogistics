package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.sql.Date;

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

        if (id != null ? !id.equals(orders.id) : orders.id != null) return false;
        if (routeId != null ? !routeId.equals(orders.routeId) : orders.routeId != null) return false;
        if (orderDate != null ? !orderDate.equals(orders.orderDate) : orders.orderDate != null) return false;
        if (paymentDay != null ? !paymentDay.equals(orders.paymentDay) : orders.paymentDay != null) return false;
        if (producerId != null ? !producerId.equals(orders.producerId) : orders.producerId != null) return false;
        if (consumerId != null ? !consumerId.equals(orders.consumerId) : orders.consumerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (routeId != null ? routeId.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (paymentDay != null ? paymentDay.hashCode() : 0);
        result = 31 * result + (producerId != null ? producerId.hashCode() : 0);
        result = 31 * result + (consumerId != null ? consumerId.hashCode() : 0);
        return result;
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
