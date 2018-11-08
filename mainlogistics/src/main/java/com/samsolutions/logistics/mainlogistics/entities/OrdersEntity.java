package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "logistic_db", catalog = "")
public class OrdersEntity {
    private Long id;
    private int routeId;
    private Date orderDate;
    private Date paymentDay;
    private int producerId;
    private int consumerId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "route_id")
    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "payment_day")
    public Date getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(Date paymentDay) {
        this.paymentDay = paymentDay;
    }

    @Basic
    @Column(name = "producer_id")
    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    @Basic
    @Column(name = "consumer_id")
    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                routeId == that.routeId &&
                producerId == that.producerId &&
                consumerId == that.consumerId &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(paymentDay, that.paymentDay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, routeId, orderDate, paymentDay, producerId, consumerId);
    }
}
