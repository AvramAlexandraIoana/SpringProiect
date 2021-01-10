package com.example.demo.controller;

import com.example.demo.dto.LocationRequest;
import com.example.demo.dto.LocationUpdate;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.model.Location;
import com.example.demo.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private LocationService locationService;
    private LocationMapper locationMapper;

    public LocationController(LocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Location> create(@RequestBody @Valid LocationRequest locationRequest) {
        Location savedLocation = locationService.create(
                locationMapper.locationRequestToLocation(locationRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("locations/" + savedLocation.getLocationId())
                .build(savedLocation.getLocationId()))
                .body(savedLocation);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Location>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(locationService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Location> update(@RequestBody @Valid LocationUpdate locationUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.update(locationMapper.locationUpdateToLocation(locationUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Location>> delete(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(locationService.delete(id));
    }
}
