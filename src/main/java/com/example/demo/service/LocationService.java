package com.example.demo.service;

import com.example.demo.model.Agency;
import com.example.demo.model.Country;
import com.example.demo.model.Location;
import com.example.demo.queries.LocationQueries;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private LocationRepository locationRepository;
    private CountryRepository countryRepository;

    public LocationService(LocationRepository locationRepository, CountryRepository countryRepository) {
        this.locationRepository = locationRepository;
        this.countryRepository = countryRepository;
    }

    public Location create(Location location) {
        return locationRepository.create(location);
    }

    public List<Location> get() {
        return locationRepository.get();
    }

    public Location update(Location location) {
        Optional<Location> existingLocationWithId = locationRepository.getById(location.getLocationId());
        if (existingLocationWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista locatia cu acest id!");
        }
        return locationRepository.update(location);
    }

    public Optional<Location> delete(int id) {
        Optional<Location> existingLocationWithId = locationRepository.getById(id);
        if (existingLocationWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista locatia cu acest id!");
        }
        return locationRepository.delete(id);
    }

    public Optional<Location> getById(int id) {
        Optional<Location> existingLocationWithId = locationRepository.getById(id);
        if (existingLocationWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista locatie cu acest id!");
        }
        return  existingLocationWithId;
    }

    public List<Location> getLocationForCountryName(String name) {
        Optional<Country> existingCountryWithName = countryRepository.getByName(name);
        if (existingCountryWithName.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista tara cu acest nume!");
        }
        return locationRepository.getLocationForCountryName(name);
    }
}
