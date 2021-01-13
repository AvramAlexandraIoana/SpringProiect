package com.example.demo.service;
import com.example.demo.model.Country;
import com.example.demo.model.Tourist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TouristServiceIT {
    @Autowired
    private TouristService touristService;

    @BeforeEach
    public void tearDown(){
        //countryService.deleteCountries();
    }

    @Test
    @DisplayName("Create tourist - happy flow")
    public void createTouristHappyFlow() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist tourist = new Tourist("Ion", "Popa", birthDate);

        Tourist result = touristService.create(tourist);

        assertNotNull(result.getTouristId());
        assertEquals(tourist.getFirstName(), result.getFirstName());
        assertEquals(tourist.getLastName(), result.getLastName());
        assertEquals(tourist.getDateOfBirth(), result.getDateOfBirth());
    }

    @Test
    @DisplayName("Get tourists - happy flow")
    public void getTouristHappyFlow(){

        List<Tourist> result = touristService.get();

        assertNotNull(result.get(0).getTouristId());
    }

    @Test
    @DisplayName("Update tourist  - happy flow")
    public void updateTouristHappyFlow() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist tourist = new Tourist(1, "Ion", "Popa", birthDate);

        Tourist result = touristService.update(tourist);

        assertNotNull(result.getTouristId());
        assertEquals(result.getFirstName(), result.getFirstName());
        assertEquals(result.getLastName(), result.getLastName());
        assertEquals(result.getDateOfBirth(), result.getDateOfBirth());
    }

    @Test
    @DisplayName("Delete tourist - happy flow")
    public void deleteCountryHappyFlow() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = format.parse("1999-10-23");
        Tourist tourist = new Tourist(5, "Ion", "Popa", birthDate);
        Optional<Tourist> result = touristService.delete(tourist.getTouristId());

        assertNotNull(result.get().getTouristId());
        assertEquals(result.get().getFirstName(), tourist.getFirstName());
        assertEquals(result.get().getLastName(), tourist.getLastName());

    }

}
