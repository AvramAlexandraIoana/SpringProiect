package com.example.demo.service;

import com.example.demo.model.Purchase;
import com.example.demo.repository.PurchaseRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase create(Purchase purchase) {
        purchase.setPurchaseDate(new Date());
        return purchaseRepository.create(purchase);
    }

    public List<Purchase> get() {
        return purchaseRepository.get();
    }

    public Purchase update(Purchase purchase) {
        Optional<Purchase> existingPurchaseWithId = purchaseRepository.getById(purchase.getTouristCode(), purchase.getTripCode());
        if (existingPurchaseWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista achizitia cu aceste id-uri!");
        }
        return purchaseRepository.update(purchase);
    }

    public Optional<Purchase> delete(int touristId, int tripId) {
        Optional<Purchase> existingPurchaseWithId = purchaseRepository.getById(touristId, tripId);
        if (existingPurchaseWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista achizitia cu aceste id-uri!");
        }
        return purchaseRepository.delete(touristId, tripId);
    }
}
