package com.example.logistics.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="routes")
public class Route {
    @Id
    private int Id;
    private String from;
    private String to;
    private int cost;

    public int getId() {
        return Id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
