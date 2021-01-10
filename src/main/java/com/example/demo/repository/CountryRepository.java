package com.example.demo.repository;

import com.example.demo.model.Country;
import com.example.demo.queries.CountryQueries;
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
public class CountryRepository {

    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CountryRepository.class);
    ;

    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Country create(Country country) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(CountryQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, country.getName());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        country.setCountryId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat tara {}", country);
        return country;
    }

    public List<Country> get() {
        List<Country> countries = jdbcTemplate.query(CountryQueries.GET_SQL, new BeanPropertyRowMapper<>(Country.class));
        logger.info("S-au preluat din repo tarile {}", countries);
        return countries;
    }

    public Country update(Country country) {
        logger.info("S-au updatat tara cu id-ul", country.getCountryId());
        jdbcTemplate.update(CountryQueries.UPDATE_SQL, country.getName(), country.getCountryId());
        return country;
    }

    public Optional<Country> delete(int id) {
        Optional<Country> country = getById(id);
        logger.info("S-au sters tara cu id-ul", id);
        jdbcTemplate.update(CountryQueries.DELETE_SQL, id);
        return country;
    }

    public Optional<Country> getById(int id) {
        logger.info("S-a preluat tara cu id-ul", id);
        return getCountryFromResultSet(jdbcTemplate.query(CountryQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Country.class), id));
    }

    public Optional<Country> getByName(int name) {
        logger.info("S-a preluat tara cu numele", name);
        return getCountryFromResultSet(jdbcTemplate.query(CountryQueries.GETBYNAME_SQL, new BeanPropertyRowMapper<>(Country.class), name));
    }

    private Optional<Country> getCountryFromResultSet(List<Country> countries) {
        if (countries != null && !countries.isEmpty()) {
            return Optional.of(countries.get(0));
        } else {
            return Optional.empty();
        }
    }

}
