package com.techelevator.dao;

import com.techelevator.entity.Brewery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCBreweryDAO implements BreweryDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCBreweryDAO(DataSource dataSource) {
        this.jdbcTemplate  = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveBrewery(Brewery brewery) {
        brewery.setBreweryId(getBreweryId());
        jdbcTemplate.update("INSERT INTO brewery (brewery_id, user_id, brewery_name, open_from, open_until, phone_number, website, email, address, history, active) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                brewery.getBreweryId(),
                brewery.getUserId(), brewery.getName(), brewery.getOpenFrom(), brewery.getOpenTo(),
                brewery.getPhoneNumber(), brewery.getWebsite(), brewery.getEmail(),
                brewery.getAddress(), brewery.getHistory(), brewery.getActive());
    }

    @Override
    public List<Brewery> getAllBreweries() {
        String sqlSelectAllBreweries ="SELECT * "+
                "FROM brewery";
        SqlRowSet brewery = jdbcTemplate.queryForRowSet(sqlSelectAllBreweries);
        List<Brewery> breweries = new ArrayList<>();
        while(brewery.next()) {
            breweries.add(buildBrewery(brewery));
        }
        return breweries;
    }

    @Override
    public void updateBrewery(Brewery brewery) {
        jdbcTemplate.update("UPDATE brewery SET brewery_name = ?, open_from = ?, open_until = ?, " +
                "phone_number = ?, website = ?, email = ?, " +
                "address = ?, history = ?, active = ?  WHERE brewery_id = ?",
                brewery.getName(), brewery.getOpenFrom(), brewery.getOpenTo(),
                brewery.getPhoneNumber(), brewery.getWebsite(), brewery.getEmail(),
                brewery.getAddress(), brewery.getHistory(), brewery.getActive(), brewery.getBreweryId());
    }

    @Override
    public Brewery getBreweryById(int breweryId) {
        Brewery theBrewery = new Brewery();
        String sqlGetBreweryById ="SELECT * FROM brewery WHERE brewery_id = ?";
        SqlRowSet brewery = jdbcTemplate.queryForRowSet(sqlGetBreweryById, breweryId);
        if(brewery.next()) {
            theBrewery = buildBrewery(brewery);
        }
        return theBrewery;

    }

    private Brewery buildBrewery(SqlRowSet brewery){
        Brewery brew = new Brewery();
        brew.setUserId(brewery.getInt("user_id"));
        brew.setName(brewery.getString("brewery_name"));
        try {
            brew.setOpenFrom(LocalTime.parse(brewery.getString("open_from")));
            brew.setOpenTo(LocalTime.parse(brewery.getString("open_until")));
        } catch (Exception e){
        }
        brew.setPhoneNumber(brewery.getString("phone_number"));
        brew.setWebsite(brewery.getString("website"));
        brew.setEmail(brewery.getString("email"));
        brew.setAddress(brewery.getString("address"));
        brew.setHistory(brewery.getString("history"));
        brew.setActive(brewery.getBoolean("active"));
        return brew;
    }

    private long getBreweryId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('brewery_brewery_id_seq')");
        if(nextIdResult.next()) {
            return nextIdResult.getLong(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new city");
        }
    }

}
