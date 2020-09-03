package com.techelevator.dao;

import com.techelevator.entity.Beer;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class JDBCBeerDAOTest extends DAOIntegrationTest {
    private JDBCBeerDAO dao;


    @Before
    public void setUp() throws Exception {
        dao = new JDBCBeerDAO(getDataSource());
    }

    @Test
    public void getBeerByIDTest(){
        Beer expectedBeer = getBeer((long) 1,"Tarek", "Tarek", 10.00, "Tarek");
        assertEquals(expectedBeer, dao.getBeerByID(1));
    }

    @Test
    public void addBeerTest(){
        Beer expectedBeer = getBeer(0L,"Beer", "Tarek", 8.00, "Tarek");
        dao.addBeer(expectedBeer, 1L);
        assertEquals(expectedBeer, dao.getBeerByID(expectedBeer.getBeerId()));
    }


    private Beer getBeer(Long beerID, String name, String description, Double abv, String beerType) {
        Beer beer = new Beer();
        beer.setBeerId(beerID);
        beer.setName(name);
        beer.setDescription(description);
        beer.setAbv(abv);
        beer.setBeerType(beerType);
        return beer;
    }
}