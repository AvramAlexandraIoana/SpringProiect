package com.example.demo.mapper;

import com.example.demo.dto.AgencyRequest;
import com.example.demo.dto.AgencyUpdate;
import com.example.demo.model.Agency;
import org.springframework.stereotype.Component;

@Component
public class AgencyMapper {
    public Agency agencyRequestToAgency(AgencyRequest agencyRequest) {
        return new Agency(agencyRequest.getName(), agencyRequest.getLocationCode());
    }

    public Agency agencyUpdateToAgency(AgencyUpdate agencyUpdate) {
        return new Agency(agencyUpdate.getAgencyId(), agencyUpdate.getName(), agencyUpdate.getLocationCode());
    }
}
