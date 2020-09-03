package com.techelevator.dao;

import com.techelevator.entity.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCBeerDAO implements BeerDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCBeerDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void addBeer(Beer beer, Long breweryId) {
        beer.setBeerId(getNextBeerId());
        jdbcTemplate.update("INSERT INTO beer(beer_id, name, description, abv, beer_type, brewery_id)" +
                "VALUES (?, ?, ?, ?, ?, ?);",
                beer.getBeerId(), beer.getName(), beer.getDescription(), beer.getAbv(), beer.getBeerType(), breweryId);
    }

    @Override
    public void deleteBeer(Beer beer) {
        jdbcTemplate.update("DELETE FROM beer WHERE name = ?", beer.getName());
    }

    @Override
    public Beer getBeerByID(long beerID) {
        Beer theBeer = null;
        String sqlFindBeerById = "SELECT beer_id, name, description, abv, beer_type, brewery_id FROM beer WHERE beer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindBeerById, beerID);
        if(results.next()){
            theBeer = mapRowToBeer(results);
        }
        return theBeer;
    }

    private Beer mapRowToBeer(SqlRowSet results){
        Beer theBeer = new Beer();
        theBeer.setBeerId(results.getLong("beer_id"));
        theBeer.setName(results.getString("name"));
        theBeer.setDescription(results.getString("description"));
        theBeer.setAbv(results.getDouble("abv"));
        theBeer.setBeerType(results.getString("beer_type"));
        return theBeer;
    }

    private long getNextBeerId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('beer_beer_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getLong(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new beer");
        }
    }




}
