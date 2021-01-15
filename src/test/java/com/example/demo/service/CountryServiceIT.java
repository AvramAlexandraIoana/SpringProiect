package com.example.demo.service;
import com.example.demo.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("Get countries - happy flow")
    public void getCountryHappyFlow(){

        List<Country> result = countryService.get();

        assertNotNull(result.get(0).getCountryId());
    }

    @Test
    @DisplayName("Update country - happy flow")
    public void updateCountryHappyFlow(){
        Country country = new Country(24, "Serbia");

        Country result = countryService.update(country);

        assertNotNull(result.getCountryId());
        assertEquals(country.getName(), result.getName());
    }

    @Test
    @DisplayName("Delete country - happy flow")
    public void deleteCountryHappyFlow(){
        Country country = new Country(24, "Serbia");

        Optional<Country> result = countryService.delete(country.getCountryId());

        assertNotNull(result.get().getCountryId());
        assertEquals(result.get().getName(), country.getName());
    }

}
