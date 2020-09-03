package com.techelevator.dao;

import com.techelevator.entity.Beer;

public interface BeerDAO {

    public void addBeer(Beer beer, Long breweryId);
    public void deleteBeer(Beer beer);
    public Beer getBeerByID(long beerID);
}
