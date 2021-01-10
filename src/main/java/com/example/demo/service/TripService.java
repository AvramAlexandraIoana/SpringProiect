package com.example.demo.service;

import com.example.demo.model.Trip;
import com.example.demo.repository.TripRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip create(Trip trip) {
        return tripRepository.create(trip);
    }

    public List<Trip> get() {
        return tripRepository.get();
    }

    public Trip update(Trip trip) {
        Optional<Trip> existingTripWithId = tripRepository.getById(trip.getTripId());
        if (existingTripWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista excursia cu acest id!");
        }
        return tripRepository.update(trip);
    }

    public Optional<Trip> delete(int id) {
        Optional<Trip> existingTripWithId = tripRepository.getById(id);
        if (existingTripWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista excursia cu acest id!");
        }
        return tripRepository.delete(id);
    }
}
