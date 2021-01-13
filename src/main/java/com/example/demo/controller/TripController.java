package com.example.demo.controller;

import com.example.demo.dto.TripRequest;
import com.example.demo.dto.TripUpdate;
import com.example.demo.mapper.TripMapper;
import com.example.demo.model.*;
import com.example.demo.service.TripService;
import com.example.demo.utils.ObjectNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Trip>> getById(@PathVariable  int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.getById(id));
    }


    /*
    Sa se afiseze toate excursiile achizitionate de turistul cu id-ul x.
     */
    @GetMapping("/getTripByTouristId")
    public  ResponseEntity<List<Trip>> getTripByTouristId(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.getTripByTouristId(id));
    }

    /*
    Sa se afiseze cel mai mare pret al unei excursii, cel mai mic pret, suma
    și media preturilor tuturor excursiilor.
     */
    @GetMapping("/getTripMinMaxSumAvgPrices")
    public ResponseEntity<List<MinMaxSumAvgPrice>> minMaxSumAvgPrices() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.minMaxSumAvgPrices());
    }

    /*
    Sa se afiseze numele excursiei, numele si prenumele turistilor care au achizitionat excursii
    mai scumpe decat turistul cu id-ul y. Sortati rezultatele dupa pret, in ordine descrecatoare.
     */
    @GetMapping("/expensiveTripThan")
    public ResponseEntity<List<TouristTrip>> expensiveTripThan(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.expensiveTripThan(id));
    }


    /*
    Sa se afiseze pentru fiecare excursie numele, pretul, destinatia si tara din care provine.
     */
    @GetMapping("/getTripWithCityAndCountryName")
    public ResponseEntity<List<TripCityAndCountryName>> getTripWithCityAndCountryName() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.getTripWithCityAndCountryName());
    }

    /*
    Să se detemine id-ul, pretul, numarul de locuri si durata primelor x cele mai scumpe excursii.
    */
    @GetMapping("/getTopOfTrips")
    public ResponseEntity<List<TripsTop>> getTopOfTrips(int number) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(tripService.getTopOfTrips(number));
    }
}
