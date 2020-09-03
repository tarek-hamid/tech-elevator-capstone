package com.techelevator.entity;

import java.util.Objects;

public class Beer {

    private long beerId;
    private String name;
    private String description;
    private double abv;
    private String beerType;

    public Beer() {}

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
