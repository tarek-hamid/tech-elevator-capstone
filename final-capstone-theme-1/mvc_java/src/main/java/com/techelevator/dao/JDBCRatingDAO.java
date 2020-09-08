package com.techelevator.dao;

import com.techelevator.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JDBCRatingDAO implements RatingDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCRatingDAO(DataSource dataSource) {
        this.jdbcTemplate  = new JdbcTemplate(dataSource);
    }

    @Override
    public void addRating(Rating rating, int beerId) {
        rating.setRatingId(getNextRatingId());
        jdbcTemplate.update("INSERT INTO rating(rating_id, beer_id, rating, rating_description)" +
                " VALUES (?, ?, ?, ?);",
                rating.getRatingId(), rating.getBeerId(), rating.getRating(), rating.getRatingDescription());

    }


//    @Override
//    public List<Rating> getallReviews() {
//        return null;
//    }

    private Rating mapRowToRating(SqlRowSet results){
        Rating theRating = new Rating();
        theRating.setRatingId(results.getLong("rating_id"));
        theRating.setBeerId(results.getInt("beer_id"));
        theRating.setRating(results.getInt("rating"));
        theRating.setRatingDescription(results.getString("rating_description"));
        return theRating;
    }

    private long getNextRatingId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('rating_rating_id_seq')");
        if (nextIdResult.next()) {
            return nextIdResult.getLong(1);
        } else {
            throw new RuntimeException("\"Something went wrong while getting a rating for a beer");
        }
    }
}
