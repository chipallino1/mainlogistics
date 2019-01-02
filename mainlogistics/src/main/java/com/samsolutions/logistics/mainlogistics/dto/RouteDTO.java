package com.samsolutions.logistics.mainlogistics.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class RouteDTO implements Serializable {

    @NotNull
    @Size(min = 2)
    private String countryFrom;
    @NotNull
    @Size(min = 2)
    private String countryTo;
    @NotNull
    @Size(min = 2)
    private String regionFrom;
    @NotNull
    @Size(min = 2)
    private String regionTo;
    @NotNull
    @Size(min = 2)
    private String cityFrom;
    @NotNull
    @Size(min = 2)
    private String cityTo;
    @NotNull
    @Size(min = 2)
    private String carName;
    @NotNull
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))T[0-23]{1,2}:[0-59]{1,2}:[0-59]{1,2}(\\+|-)\\d{4}$")
    private String dateStart;
    @NotNull
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))T[0-23]{1,2}:[0-59]{1,2}:[0-59]{1,2}(\\+|-)\\d{4}$")
    private String dateFinish;
    @NotNull
    @Size(min = 1)
    private Long volume;
    @NotNull
    @Size(min = 1)
    private Long capacity;
    @NotNull
    @Size(min = 1)
    private Long length;
    @NotNull
    @Size(min=1)
    private Long cost;

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(String countryTo) {
        this.countryTo = countryTo;
    }

    public String getRegionFrom() {
        return regionFrom;
    }

    public void setRegionFrom(String regionFrom) {
        this.regionFrom = regionFrom;
    }

    public String getRegionTo() {
        return regionTo;
    }

    public void setRegionTo(String regionTo) {
        this.regionTo = regionTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
