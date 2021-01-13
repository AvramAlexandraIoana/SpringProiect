package com.example.demo.controller;


import com.example.demo.dto.PurchaseRequest;
import com.example.demo.dto.PurchaseUpdate;
import com.example.demo.mapper.PurchaseMapper;
import com.example.demo.model.Location;
import com.example.demo.model.Purchase;
import com.example.demo.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private PurchaseService purchaseService;
    private PurchaseMapper purchaseMapper;

    public PurchaseController(PurchaseService purchaseService, PurchaseMapper purchaseMapper) {
        this.purchaseService = purchaseService;
        this.purchaseMapper = purchaseMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<Purchase> create(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        Purchase savedPurchase = purchaseService.create(
                purchaseMapper.purchaseRequestToPurchase(purchaseRequest));
        return ResponseEntity.created(UriComponentsBuilder.
                fromHttpUrl(ServletUriComponentsBuilder.
                        fromCurrentRequestUri().
                        toUriString())
                .replacePath("purchases/" + savedPurchase.getTouristCode())
                .build(savedPurchase.getTouristCode()))
                .body(savedPurchase);

    }

    @GetMapping("/get")
    public ResponseEntity<List<Purchase>> get() {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(purchaseService.get());
    }

    @PutMapping("/update")
    public ResponseEntity<Purchase> update(@RequestBody @Valid PurchaseUpdate purchaseUpdate) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseService.update(purchaseMapper.purchaseUpdateToPurchase(purchaseUpdate)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<Purchase>> delete(@RequestParam int touristId, @RequestParam int tripId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseService.delete(touristId, tripId));
    }

    @GetMapping("/{touristId}/{tripId}")
    public ResponseEntity<Optional<Purchase>> getById(@PathVariable  int touristId, @PathVariable  int tripId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(purchaseService.getById(touristId, tripId));
    }
}
