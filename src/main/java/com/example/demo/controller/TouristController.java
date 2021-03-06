package com.example.demo.controller;

import com.example.demo.dto.TouristRequest;
import com.example.demo.dto.TouristUpdate;
import com.example.demo.mapper.TouristMapper;
import com.example.demo.model.Location;
import com.example.demo.model.Tourist;
import com.example.demo.model.TouristNumberOfTrips;
import com.example.demo.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tourists")
public class TouristController {
    private TouristService touristService;
    private TouristMapper touristMapper;

    public TouristController(TouristService touristService, TouristMapper touristMapper) {
        this.touristService = touristService;
        this.touristMapper = touristMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Tourist> create(@RequestBody @Valid TouristRequest touristRequest) {
        Tourist savedTourist = touristService.create(
                touristMapper.touristRequestToTourist(touristRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("tourists/" + savedTourist.getTouristId())
                .build(savedTourist.getTouristId()))
                .body(savedTourist);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Tourist>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(touristService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Tourist> update(@RequestBody @Valid TouristUpdate touristUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.update(touristMapper.touristUpdateToTourist(touristUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Tourist>> delete(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Tourist>> getById(@PathVariable  int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.getById(id));
    }

    /*
    Sa se afiseze turistii ordonati crescator/descrescator dupa nume.
     */
    @GetMapping("/order")
    private ResponseEntity<List<Tourist>> orderTurists(@RequestParam String type) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.orderTurists(type));
    }

    /*
    Sa se determine toti turistii nascuti in anul x.
     */
    @GetMapping("/getByBirthDate")
    public ResponseEntity<List<Tourist>> getByBirthDate(int year) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.getByBirthDate(year));
    }

    /*
    Sa se afiseze numele, prenumele turistului si numarul de excursii achizitonate.
     */
    @GetMapping("/getTouristWithNumberOfTrips")
    public ResponseEntity<List<TouristNumberOfTrips>> getTouristWithNumberOfTrips() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(touristService.getTouristWithNumberOfTrips());
    }
}
