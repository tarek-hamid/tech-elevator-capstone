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
        jdbcTemplate.update("INSERT INTO brewery (user_id, brewery_name, open_from, open_until, phone_number, website, email, address, history, active) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                brewery.getUserId(), brewery.getName(), brewery.getOpenFrom(), brewery.getOpenTo(),
                brewery.getPhoneNumber(), brewery.getWebsite(), brewery.getEmail(),
                brewery.getAddress(), brewery.getHistory(), brewery.getActive());
    }

//    @Override
//    public User getUserByUserName(String userName) {
//        String sqlSearchForUsername ="SELECT * "+
//                "FROM app_user "+
//                "WHERE UPPER(user_name) = ? ";
//
//        SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase());
//        User thisUser = null;
//        if(user.next()) {
//            thisUser = new User();
//            thisUser.setId(user.getLong("id"));
//            thisUser.setUserName(user.getString("user_name"));
//            thisUser.setPassword(user.getString("password"));
//            thisUser.setFirstName(user.getString("first_name"));
//            thisUser.setLastName(user.getString("last_name"));
//            thisUser.setRole(user.getString("role"));
//        }
//
//        return thisUser;
   // }


    @Override
    public List<Brewery> getAllBreweries() {
        String sqlSelectAllBreweries ="SELECT * "+
                "FROM brewery";
        SqlRowSet brewery = jdbcTemplate.queryForRowSet(sqlSelectAllBreweries);
        List<Brewery> breweries = new ArrayList<>();
        if(brewery.next()) {
            breweries.add(buildBrewery(brewery));
        }
        return breweries;
    }

    private Brewery buildBrewery(SqlRowSet brewery){
        Brewery brew = new Brewery();
        brew.setUserId(brewery.getInt("user_id"));
        brew.setName(brewery.getString("brewery_name"));
        brew.setOpenFrom(LocalTime.parse(brewery.getString("open_from")));
        brew.setOpenTo(LocalTime.parse(brewery.getString("open_until")));
        brew.setPhoneNumber(brewery.getString("phone_number"));
        brew.setWebsite(brewery.getString("website"));
        brew.setEmail(brewery.getString("email"));
        brew.setAddress(brewery.getString("address"));
        brew.setHistory(brewery.getString("history"));
        brew.setActive(brewery.getBoolean("active"));
        return brew;
    }

}
