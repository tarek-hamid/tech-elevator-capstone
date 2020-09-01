package com.techelevator.dao;

import com.techelevator.entity.Brewery;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JDBCBreweryDAO implements BreweryDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCBreweryDAO(DataSource dataSource) {
        this.jdbcTemplate  = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveBrewery(Brewery brewery) {
        jdbcTemplate.update("INSERT INTO brewery(brewery_name, phone_number, website, email, address, history, active) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                brewery.getName(), brewery.getPhoneNumber(), brewery.getWebsite(), brewery.getEmail(),
                brewery.getAddress(), brewery.getHistory(), brewery.getActive());
    }
}
