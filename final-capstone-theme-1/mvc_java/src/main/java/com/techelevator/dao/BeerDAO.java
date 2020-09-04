package com.techelevator.dao;

import com.techelevator.entity.Beer;

import java.util.List;

public interface BeerDAO {

    public void addBeer(Beer beer, Long breweryId);
    public void deleteBeer(int beerId);
    public Beer getBeerByID(long beerID);
    public List<Beer> getBeerByBreweryId(int breweryId);
}
