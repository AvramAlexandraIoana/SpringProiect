package com.example.demo.service;
import com.example.demo.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CountryServiceIT {
    @Autowired
    private CountryService countryService;

    @BeforeEach
    public void tearDown(){
        //countryService.deleteCountries();
    }

    @Test
    @DisplayName("Create country - happy flow")
    public void createCountryHappyFlow(){
        Country country = new Country("S.U.A");

        Country result = countryService.create(country);

        assertNotNull(result.getCountryId());
        assertEquals(country.getName(), result.getName());
    }

}
