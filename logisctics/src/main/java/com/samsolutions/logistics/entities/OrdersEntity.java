package com.samsolutions.logistics.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders", schema = "logistic_db", catalog = "")
public class OrdersEntity {
    private int id;
    private Date orderDate;
    private Date paymentDay;
    private RoutesEntity routesByRouteId;
    private FirmsEntity firmsByProducerId;
    private FirmsEntity firmsByConsumerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (paymentDay != null ? !paymentDay.equals(that.paymentDay) : that.paymentDay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (paymentDay != null ? paymentDay.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false)
    public RoutesEntity getRoutesByRouteId() {
        return routesByRouteId;
    }

    public void setRoutesByRouteId(RoutesEntity routesByRouteId) {
        this.routesByRouteId = routesByRouteId;
    }

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id", nullable = false)
    public FirmsEntity getFirmsByProducerId() {
        return firmsByProducerId;
    }

    public void setFirmsByProducerId(FirmsEntity firmsByProducerId) {
        this.firmsByProducerId = firmsByProducerId;
    }

    @ManyToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "id", nullable = false)
    public FirmsEntity getFirmsByConsumerId() {
        return firmsByConsumerId;
    }

    public void setFirmsByConsumerId(FirmsEntity firmsByConsumerId) {
        this.firmsByConsumerId = firmsByConsumerId;
    }
}
