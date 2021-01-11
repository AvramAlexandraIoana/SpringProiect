package com.example.demo.repository;

import com.example.demo.model.Tourist;
import com.example.demo.queries.TouristQueries;
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
public class TouristRepository {

    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(TouristRepository.class);
    ;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tourist create(Tourist tourist) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(TouristQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, tourist.getFirstName());
            preparedStatement.setString(3, tourist.getLastName());
            preparedStatement.setDate(4, new java.sql.Date(tourist.getDateOfBirth().getTime()));
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        tourist.setTouristId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat turistul {}", tourist);
        return tourist;
    }

    public List<Tourist> get() {
        List<Tourist> tourists = jdbcTemplate.query(TouristQueries.GET_SQL, new BeanPropertyRowMapper<>(Tourist.class));
        logger.info("S-au preluat din repo turistii {}", tourists);
        return tourists;
    }

    public Tourist update(Tourist tourist) {
        logger.info("S-au updatat turistul cu id-ul", tourist.getTouristId());
        jdbcTemplate.update(TouristQueries.UPDATE_SQL, tourist.getFirstName(), tourist.getLastName(), tourist.getDateOfBirth(), tourist.getTouristId());
        return tourist;
    }

    public Optional<Tourist> delete(int id) {
        Optional<Tourist> tourist = getById(id);
        logger.info("S-au sters turistul cu id-ul", id);
        jdbcTemplate.update(TouristQueries.DELETE_SQL, id);
        return tourist;
    }

    public Optional<Tourist> getById(int id) {
        logger.info("S-a preluat turistul cu id-ul", id);
        return getTouristFromResultSet(jdbcTemplate.query(TouristQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Tourist.class), id));
    }


    private Optional<Tourist> getTouristFromResultSet(List<Tourist> tourists) {
        if (tourists != null && !tourists.isEmpty()) {
            return Optional.of(tourists.get(0));
        } else {
            return Optional.empty();
        }
    }

    public List<Tourist> orderTuristsAsc() {
        List<Tourist> tourists = jdbcTemplate.query(TouristQueries.ORDER_ASC_SQL, new BeanPropertyRowMapper<>(Tourist.class));
        logger.info("Ordam turistii crescator ", tourists);
        return tourists;
    }

    public List<Tourist> orderTuristsDesc() {
        List<Tourist> tourists = jdbcTemplate.query(TouristQueries.ORDER_DESC_SQL, new BeanPropertyRowMapper<>(Tourist.class));
        logger.info("Ordam turistii descrecator ", tourists);
        return tourists;
    }

}
