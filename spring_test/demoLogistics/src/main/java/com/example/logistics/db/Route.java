package com.example.logistics.db;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="routes")
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "point_from")
    private String from;
    @Column(name="point_to")
    private String to;
    @Column
    private int cost;

    public Long getId() {
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

    public void setId(Long id) {
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
