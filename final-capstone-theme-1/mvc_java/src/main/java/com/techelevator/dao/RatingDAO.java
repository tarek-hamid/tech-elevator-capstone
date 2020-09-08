package com.techelevator.dao;

import com.techelevator.entity.Brewery;
import com.techelevator.entity.Rating;

import java.util.List;

public interface RatingDAO {

    public void addRating(Rating rating);
    public List<Rating> getAllReviewsByBeerId(int beerId);
}

