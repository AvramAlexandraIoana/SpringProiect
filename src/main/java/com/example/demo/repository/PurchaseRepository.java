package com.example.demo.repository;

import com.example.demo.model.Purchase;
import com.example.demo.queries.PurchaseQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchaseRepository {
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(LocationRepository.class);
    ;

    public PurchaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Purchase create(Purchase purchase) {
        jdbcTemplate.update(PurchaseQueries.CREATE_SQL, purchase.getTouristCode(), purchase.getTripCode(), purchase.getStartDate(),
                purchase.getEndDate(), purchase.getPurchaseDate(), purchase.getDiscount());
        logger.info("S-a adaugat achizitia {}", purchase);
        return purchase;
    }

    public List<Purchase> get() {
        List<Purchase> purchases = jdbcTemplate.query(PurchaseQueries.GET_SQL, new BeanPropertyRowMapper<>(Purchase.class));
        logger.info("S-au preluat din repo achizitiile {}", purchases);
        return purchases;
    }

    public Purchase update(Purchase purchase) {
        logger.info("S-au updatat achizitia cu id-ul turistului ", purchase.getTouristCode(), " si id-ul excursiei", purchase.getTripCode());
        jdbcTemplate.update(PurchaseQueries.UPDATE_SQL, purchase.getStartDate(), purchase.getEndDate(), purchase.getPurchaseDate(),
                purchase.getDiscount(), purchase.getTouristCode(), purchase.getTripCode());
        return purchase;
    }

    public Optional<Purchase> delete(int touristId, int tripId) {
        Optional<Purchase> location = getById(touristId, tripId);
        logger.info("S-au sters achizita cu id-ul turistului", touristId, " si id-ul excursiei ", tripId);
        jdbcTemplate.update(PurchaseQueries.DELETE_SQL, touristId, tripId);
        return location;
    }

    public Optional<Purchase> getById(int touristId, int tripId) {
        logger.info("S-a preluat achizita cu id-ul turistului", touristId, " si id-ul excursiei ", tripId);
        return getPurchaseFromResultSet(jdbcTemplate.query(PurchaseQueries.GETBYID_SQL, new BeanPropertyRowMapper<>(Purchase.class), touristId, tripId));
    }


    private Optional<Purchase> getPurchaseFromResultSet(List<Purchase> purchases) {
        if (purchases != null && !purchases.isEmpty()) {
            return Optional.of(purchases.get(0));
        } else {
            return Optional.empty();
        }
    }

}
