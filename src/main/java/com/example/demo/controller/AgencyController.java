package com.example.demo.controller;

import com.example.demo.dto.AgencyRequest;
import com.example.demo.dto.AgencyUpdate;
import com.example.demo.mapper.AgencyMapper;
import com.example.demo.model.Agency;
import com.example.demo.model.AgencyWithNumberOfTrips;
import com.example.demo.service.AgencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agencies")
public class AgencyController {
    private AgencyService agencyService;
    private AgencyMapper agencyMapper;

    public AgencyController(AgencyService agencyService, AgencyMapper agencyMapper) {
        this.agencyService = agencyService;
        this.agencyMapper = agencyMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Agency> create(@RequestBody @Valid AgencyRequest agencyRequest) {
        Agency savedAgency = agencyService.create(
                agencyMapper.agencyRequestToAgency(agencyRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("agencies/" + savedAgency.getAgencyId())
                .build(savedAgency.getAgencyId()))
                .body(savedAgency);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Agency>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(agencyService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Agency> update(@RequestBody @Valid AgencyUpdate agencyUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.update(agencyMapper.agencyUpdateToAgency(agencyUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Agency>> delete(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.delete(id));
    }

    /*
     Sa se afiseze id-ul, numele agentiei si numarul de excursii de la acea agentie
     */
    @GetMapping("/agencyWithNumberOfTrips")
    public ResponseEntity<List<AgencyWithNumberOfTrips>> agencyWithNumberOfTrips() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(agencyService.agencyWithNumberOfTrips());
    }
}
