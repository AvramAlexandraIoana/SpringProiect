package com.example.demo.mapper;

import com.example.demo.dto.LocationRequest;
import com.example.demo.dto.LocationUpdate;
import com.example.demo.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location locationRequestToLocation(LocationRequest locationRequest) {
        return new Location(locationRequest.getAddress(), locationRequest.getCountryCode());
    }

    public Location locationUpdateToLocation(LocationUpdate locationUpdate) {
        return new Location(locationUpdate.getLocationId(), locationUpdate.getAddress(), locationUpdate.getCountryCode());
    }
}
