package com.example.demo.controller;

import com.example.demo.dto.CountryRequest;
import com.example.demo.dto.CountryUpdate;
import com.example.demo.mapper.CountryMapper;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private CountryService countryService;
    private CountryMapper countryMapper;

    public CountryController(CountryService countryService, CountryMapper countryMapper) {
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Country> create(@RequestBody @Valid CountryRequest countryRequest) {
        Country savedCountry = countryService.create(
                countryMapper.countryRequestToCountry(countryRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("countries/" + savedCountry.getCountryId())
                .build(savedCountry.getCountryId()))
                .body(savedCountry);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Country>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(countryService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Country> update(@RequestBody @Valid CountryUpdate countryUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.update(countryMapper.countryUpdateToCountry(countryUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Country>> delete(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(countryService.delete(id));
    }
}
