package com.example.demo.service;

import com.example.demo.model.MinMaxSumAvgPrice;
import com.example.demo.model.Purchase;
import com.example.demo.model.Tourist;
import com.example.demo.model.Trip;
import com.example.demo.queries.TripQueries;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.repository.TouristRepository;
import com.example.demo.repository.TripRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private TripRepository tripRepository;
    private PurchaseRepository purchaseRepository;

    public TripService(TripRepository tripRepository, PurchaseRepository purchaseRepository) {
        this.tripRepository = tripRepository;
        this.purchaseRepository = purchaseRepository;
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

    public  List<Trip> getTripByTouristId(int id) {
        Optional<Purchase> existingPurchaseWithTouristId = purchaseRepository.getByTouristId(id);
        if (existingPurchaseWithTouristId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista nicio excursie achizitionata de turistul cu acest id!");
        }
        return tripRepository.getTripByTouristId(id);
    }

    public List<MinMaxSumAvgPrice> minMaxSumAvgPrices() {
        return tripRepository.minMaxSumAvgPrices();
    }
}
