package com.techelevator.dao;

import com.techelevator.entity.Brewery;

import java.util.List;

public interface BreweryDAO {

    public void saveBrewery(Brewery brewery);

    public List<Brewery> getAllBreweries();

    public void updateBrewery(Brewery brewery);

    public Brewery getBreweryById(int breweryId);
}
