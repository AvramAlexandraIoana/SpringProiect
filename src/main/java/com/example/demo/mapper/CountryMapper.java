package com.example.demo.mapper;

import com.example.demo.dto.CountryRequest;
import com.example.demo.dto.CountryUpdate;
import com.example.demo.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public Country countryRequestToCountry(CountryRequest countryRequest) {
        return new Country(countryRequest.getName());
    }

    public Country countryUpdateToCountry(CountryUpdate countryUpdate) {
        return new Country(countryUpdate.getCountryId(), countryUpdate.getName());
    }
}
