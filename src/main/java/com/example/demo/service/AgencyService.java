package com.example.demo.service;

import com.example.demo.model.Agency;
import com.example.demo.repository.AgencyRepository;
import com.example.demo.utils.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AgencyService {
    private AgencyRepository agencyRepository;

    public AgencyService(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public Agency create(Agency agency) {
        return agencyRepository.create(agency);
    }

    public List<Agency> get() {
        return agencyRepository.get();
    }

    public Agency update(Agency agency) {
        Optional<Agency> existingAgencyWithId = agencyRepository.getById(agency.getAgencyId());
        if (existingAgencyWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista agentia cu acest id!");
        }
        return agencyRepository.update(agency);
    }

    public Optional<Agency> delete(int id) {
        Optional<Agency> existingAgencyWithId = agencyRepository.getById(id);
        if (existingAgencyWithId.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista agentia cu acest id!");
        }
        return agencyRepository.delete(id);
    }
}
