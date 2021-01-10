package com.example.demo.mapper;

import com.example.demo.dto.TripRequest;
import com.example.demo.dto.TripUpdate;
import com.example.demo.model.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    public Trip tripRequestToTrip(TripRequest tripRequest) {
        return new Trip(tripRequest.getName(), tripRequest.getPrice(), tripRequest.getNumberOfSeats(),
                tripRequest.getDuration(), tripRequest.getAgencyId(), tripRequest.getLocationId());
    }

    public Trip tripUpdateToTrip(TripUpdate tripUpdate) {
        return new Trip(tripUpdate.getTripId(), tripUpdate.getName(), tripUpdate.getPrice(), tripUpdate.getNumberOfSeats(),
                tripUpdate.getDuration(), tripUpdate.getAgencyId(), tripUpdate.getLocationId());
    }
}
