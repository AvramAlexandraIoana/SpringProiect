package com.example.demo.repository;

import com.example.demo.model.Location;
import com.example.demo.queries.LocationQueries;
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
public class LocationRepository {

    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LocationRepository.class);
    ;

    public LocationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Location create(Location location) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(LocationQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getAddress());
            preparedStatement.setInt(4, location.getCountryCode());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        location.setLocationId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat locatia {}", location);
        return location;
    }

    public List<Location> get() {
        List<Location> locations = jdbcTemplate.query(LocationQueries.GET_SQL, new BeanPropertyRowMapper<>(Location.class));
        logger.info("S-au preluat din repo locatiile {}", locations);
        return locations;
    }

    public Location update(Location location) {
        logger.info("S-au updatat locatia cu id-ul "  + location.getLocationId() + " {}", location);
        jdbcTemplate.update(LocationQueries.UPDATE_SQL, location.getCity(), location.getAddress(), location.getCountryCode(), location.getLocationId());
        return location;
    }

    public Optional<Location> delete(int id) {
        Optional<Location> location = getById(id);
        logger.info("S-au sters locatia cu id-ul " + id + " {}", location);
        jdbcTemplate.update(LocationQueries.DELETE_SQL, id);
        return location;
    }

    public Optional<Location> getById(int id) {
        Optional<Location> location = getLocationFromResultSet(jdbcTemplate.query(LocationQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Location.class), id));
        logger.info("S-a preluat locatia cu id-ul " + id + " {}", location);
        return location;
    }


    private Optional<Location> getLocationFromResultSet(List<Location> locations) {
        if (locations != null && !locations.isEmpty()) {
            return Optional.of(locations.get(0));
        } else {
            return Optional.empty();
        }
    }

    public List<Location> getLocationForCountryName(String name) {
        List<Location> locations = jdbcTemplate.query(LocationQueries.GETLOCATIONFORCOUNTRYNAME_SQL, new BeanPropertyRowMapper<>(Location.class), name);
        logger.info("S-a preluat locatiile din tara cu numele " + name + " {}", locations);
        return  locations;
    }


}
