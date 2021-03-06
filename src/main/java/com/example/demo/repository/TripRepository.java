package com.example.demo.repository;

import com.example.demo.model.*;
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
        logger.info("S-au updatat excursia cu id-ul " + trip.getTripId() + " {}", trip );
        jdbcTemplate.update(TripQueries.UPDATE_SQL, trip.getName(), trip.getPrice(), trip.getNumberOfSeats(), trip.getDuration(),
                trip.getAgencyId(), trip.getLocationId(), trip.getTripId());
        return trip;
    }

    public Optional<Trip> delete(int id) {
        Optional<Trip> trip = getById(id);
        logger.info("S-au sters excursia cu id-ul " + id + " {} ", trip);
        jdbcTemplate.update(TripQueries.DELETE_SQL, id);
        return trip;
    }

    public Optional<Trip> getById(int id) {
        Optional<Trip> trip = getTripFromResultSet(jdbcTemplate.query(TripQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Trip.class), id));
        logger.info("S-a preluat excursia cu id-ul " + id + " {} ", trip);
        return  trip;
    }


    private Optional<Trip> getTripFromResultSet(List<Trip> trips) {
        if (trips != null && !trips.isEmpty()) {
            return Optional.of(trips.get(0));
        } else {
            return Optional.empty();
        }
    }

    public  List<Trip> getTripByTouristId(int id) {
        List<Trip> trips = jdbcTemplate.query(TripQueries.GETBYTOURISTID_SQL, new BeanPropertyRowMapper<>(Trip.class), id);
        logger.info("S-a preluat excursiile achizitionate de turistul cu id-ul " + id + " {}", trips);
        return  trips;
    }

    public List<MinMaxSumAvgPrice> minMaxSumAvgPrices() {
        List<MinMaxSumAvgPrice>  minMaxSumAvgPrices = jdbcTemplate.query(TripQueries.MINMAXSUMAVGPRICE_SQL, new BeanPropertyRowMapper<>(MinMaxSumAvgPrice.class));
        logger.info("S-a preluat cel mai mare pret al unei excursii, cel mai mic pret, suma și media preturilor tuturor excursiilor {}", minMaxSumAvgPrices);
        return minMaxSumAvgPrices;
    }

    public List<TouristTrip> expensiveTripThan(int id) {
        List<TouristTrip>  touristTrips = jdbcTemplate.query(TripQueries.EXPENSIVETRIPSTHAN_SQL, new BeanPropertyRowMapper<>(TouristTrip.class), id);
        logger.info("Se preia numele excursiei, numele si prenumele turistilor care au achizitionat excursii mai scumpe decat turistul cu id-ul y. Sortati rezultatele dupa pret, in ordine descrecatoare " + id + "{}", touristTrips);
        return touristTrips;
    }

    public List<TripCityAndCountryName> getTripWithCityAndCountryName() {
        List<TripCityAndCountryName> tripCityAndCountryNames = jdbcTemplate.query(TripQueries.GETTRIPWITHCITYANDCOUNTRY_SQL, new BeanPropertyRowMapper<>(TripCityAndCountryName.class));
        logger.info("Se preiau pentru  fiecare excursie numele, pretul, destinatia si tara din care provine {}", tripCityAndCountryNames);
        return tripCityAndCountryNames;
    }

    public List<TripsTop> getTopOfTrips(int number) {
        List<TripsTop> tripsTops = jdbcTemplate.query(TripQueries.TOPOFTRIPS_SQL, new BeanPropertyRowMapper<>(TripsTop.class), number);
        logger.info("Se preiau primele " + number + " cele mai scumpe excursii. {}", tripsTops);
        return tripsTops;
    }


}
