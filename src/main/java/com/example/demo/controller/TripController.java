package com.example.demo.controller;

import com.example.demo.dto.TripRequest;
import com.example.demo.dto.TripUpdate;
import com.example.demo.mapper.TripMapper;
import com.example.demo.model.Trip;
import com.example.demo.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trips")
public class TripController {
    private TripService tripService;
    private TripMapper tripMapper;

    public TripController(TripService tripService, TripMapper tripMapper) {
        this.tripService = tripService;
        this.tripMapper = tripMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Trip> create(@RequestBody @Valid TripRequest tripRequest) {
        Trip savedTrip = tripService.create(
                tripMapper.tripRequestToTrip(tripRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("trips/" + savedTrip.getTripId())
                .build(savedTrip.getTripId()))
                .body(savedTrip);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Trip>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(tripService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Trip> update(@RequestBody @Valid TripUpdate tripUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.update(tripMapper.tripUpdateToTrip(tripUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Trip>> delete(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.delete(id));
    }
}
