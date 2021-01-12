package com.example.demo.service;

import com.example.demo.model.Tourist;
import com.example.demo.queries.TouristQueries;
import com.example.demo.repository.TouristRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public Tourist create(Tourist tourist) {
        return touristRepository.create(tourist);
    }

    public List<Tourist> get() {
        return touristRepository.get();
    }

    public Tourist update(Tourist tourist) {
        Optional<Tourist> existingTouristWithId = touristRepository.getById(tourist.getTouristId());
        if (existingTouristWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista turist cu acest id!");
        }
        return touristRepository.update(tourist);
    }

    public Optional<Tourist> delete(int id) {
        Optional<Tourist> existingTouristWithId = touristRepository.getById(id);
        if (existingTouristWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista turist cu acest id!");
        }
        return touristRepository.delete(id);
    }

    public List<Tourist> orderTurists(String type) {
        return type.equals("asc") ? touristRepository.orderTuristsAsc() : touristRepository.orderTuristsDesc();
    }

    public List<Tourist> getByBirthDate(int year) {
        return touristRepository.getByBirthDate(year);
    }
}
