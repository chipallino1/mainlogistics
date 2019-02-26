package com.samsolutions.logistics.mainlogistics.entities;

import javax.persistence.*;
import java.util.Collection;

//@Entity
@Table(name = "POINTS")
public class Points {

    private Long id;
    private String country;
    private String region;
    private String city;
    private Collection<Routes> routesByPointFromId;
    private Collection<Routes> routesByPointToId;

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
    @Column(name="country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name="region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name="city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @OneToMany(mappedBy = "pointsByPointFromId")
    public Collection<Routes> getRoutesByPointFromId() {
        return routesByPointFromId;
    }

    public void setRoutesByPointFromId(Collection<Routes> routesByPointFromId) {
        this.routesByPointFromId = routesByPointFromId;
    }

    @OneToMany(mappedBy = "pointsByPointToId")
    public Collection<Routes> getRoutesByPointToId() {
        return routesByPointToId;
    }

    public void setRoutesByPointToId(Collection<Routes> routesByPointToId) {
        this.routesByPointToId = routesByPointToId;
    }
}
