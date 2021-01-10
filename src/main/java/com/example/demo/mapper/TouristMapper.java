package com.example.demo.mapper;

import com.example.demo.dto.TouristRequest;
import com.example.demo.dto.TouristUpdate;
import com.example.demo.model.Tourist;
import org.springframework.stereotype.Component;

@Component
public class TouristMapper {
    public Tourist touristRequestToTourist(TouristRequest touristRequest) {
        return new Tourist(touristRequest.getFirstName(), touristRequest.getLastName(), touristRequest.getDateOfBirth());
    }

    public Tourist touristUpdateToTourist(TouristUpdate touristUpdate) {
        return new Tourist(touristUpdate.getTouristId(), touristUpdate.getFirstName(), touristUpdate.getLastName(), touristUpdate.getDateOfBirth());
    }

}
