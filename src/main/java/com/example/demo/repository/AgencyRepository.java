package com.example.demo.repository;

import com.example.demo.model.Agency;
import com.example.demo.queries.AgencyQueries;
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
public class AgencyRepository {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AgencyRepository.class);
    ;

    public AgencyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Agency create(Agency agency) {
        PreparedStatementCreator preparedStatementCreator = (connection) -> {
            PreparedStatement preparedStatement = connection.prepareStatement(AgencyQueries.CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, null);
            preparedStatement.setString(2, agency.getName());
            preparedStatement.setInt(3, agency.getLocationCode());
            return preparedStatement;
        };
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, generatedKeyHolder);
        agency.setAgencyId(generatedKeyHolder.getKey().intValue());
        logger.info("S-a adaugat agentia {}", agency);
        return agency;
    }

    public List<Agency> get() {
        List<Agency> agencies = jdbcTemplate.query(AgencyQueries.GET_SQL, new BeanPropertyRowMapper<>(Agency.class));
        logger.info("S-au preluat din repo agentiile {}", agencies);
        return agencies;
    }

    public Agency update(Agency agency) {
        logger.info("S-au updatat agentia cu id-ul", agency.getAgencyId());
        jdbcTemplate.update(AgencyQueries.UPDATE_SQL, agency.getName(), agency.getLocationCode(), agency.getAgencyId());
        return agency;
    }

    public Optional<Agency> delete(int id) {
        Optional<Agency> agency = getById(id);
        logger.info("S-au sters agentia cu id-ul", id);
        jdbcTemplate.update(AgencyQueries.DELETE_SQL, id);
        return agency;
    }

    public Optional<Agency> getById(int id) {
        logger.info("S-a preluat agentia cu id-ul", id);
        return getAgencyFromResultSet(jdbcTemplate.query(AgencyQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Agency.class), id));
    }


    private Optional<Agency> getAgencyFromResultSet(List<Agency> agencies) {
        if (agencies != null && !agencies.isEmpty()) {
            return Optional.of(agencies.get(0));
        } else {
            return Optional.empty();
        }
    }
}
