package com.example.demo.repository;

import com.example.demo.model.Trip;
import com.example.demo.queries.TripQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TripRepository {

    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TripRepository.class);
    ;

    public TripRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Trip create(Trip trip) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(TripQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, trip.getName());
            preparedStatement.setDouble(3, trip.getPrice());
            preparedStatement.setInt(4, trip.getNumberOfSeats());
            preparedStatement.setInt(5, trip.getDuration());
            preparedStatement.setInt(6, trip.getAgencyId());
            preparedStatement.setInt(7, trip.getLocationId());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        trip.setTripId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat excursia {}", trip);
        return trip;
    }

    public List<Trip> get() {
        List<Trip> trips = jdbcTemplate.query(TripQueries.GET_SQL, new BeanPropertyRowMapper<>(Trip.class));
        logger.info("S-au preluat din repo excursiile {}", trips);
        return trips;
    }

    public Trip update(Trip trip) {
        logger.info("S-au updatat excursia cu id-ul", trip.getTripId());
        jdbcTemplate.update(TripQueries.UPDATE_SQL, trip.getName(), trip.getPrice(), trip.getNumberOfSeats(), trip.getDuration(),
                trip.getAgencyId(), trip.getLocationId(), trip.getTripId());
        return trip;
    }

    public Optional<Trip> delete(int id) {
        Optional<Trip> trip = getById(id);
        logger.info("S-au sters excursia cu id-ul", id);
        jdbcTemplate.update(TripQueries.DELETE_SQL, id);
        return trip;
    }

    public Optional<Trip> getById(int id) {
        logger.info("S-a preluat excursia cu id-ul", id);
        return getTripFromResultSet(jdbcTemplate.query(TripQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Trip.class), id));
    }


    private Optional<Trip> getTripFromResultSet(List<Trip> trips) {
        if (trips != null && !trips.isEmpty()) {
            return Optional.of(trips.get(0));
        } else {
            return Optional.empty();
        }
    }
}
