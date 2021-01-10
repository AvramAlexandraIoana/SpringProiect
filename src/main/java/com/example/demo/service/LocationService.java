package com.example.demo.service;

import com.example.demo.model.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
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
}
