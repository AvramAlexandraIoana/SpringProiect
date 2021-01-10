package com.example.demo.service;

import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Country create(Country country) {
        return countryRepository.create(country);
    }

    public List<Country> get() {
        return countryRepository.get();
    }

    public Country update(Country country) {
        Optional<Country> existingCountryWithId = countryRepository.getById(country.getCountryId());
        if (existingCountryWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista tara cu acest id!");
        }
        return countryRepository.update(country);
    }

    public Optional<Country> delete(int id) {
        Optional<Country> existingCountryWithId = countryRepository.getById(id);
        if (existingCountryWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista tara cu acest id!");
        }
        return countryRepository.delete(id);
    }
}
