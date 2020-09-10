package com.techelevator.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Beer {

    private long beerId;

    @NotBlank(message="Please name the beer")
    private String name;
    @NotBlank(message = "please describe the beer")
    private String description;
    @Range(min = (long) 0.0, max = (long) 50.0)
    private double abv;
    @NotBlank(message = "What type of beer is it?")
    private String beerType;

    private int breweryId;

    public Beer() {}

    public Beer(int breweryId) {
        this.breweryId = breweryId;
    }

    public long getBeerId() {
        return beerId;
    }

    public void setBeerId(long beerId) {
        this.beerId = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String getBeerType() {
        return beerType;
    }

    public void setBeerType(String beerType) {
        this.beerType = beerType;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return beerId == beer.beerId &&
                Double.compare(beer.abv, abv) == 0 &&
                Objects.equals(name, beer.name) &&
                Objects.equals(description, beer.description) &&
                Objects.equals(beerType, beer.beerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beerId, name, description, abv, beerType);
    }
}
