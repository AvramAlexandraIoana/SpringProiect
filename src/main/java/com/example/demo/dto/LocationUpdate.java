package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationUpdate {
    @NotNull(message = "Id-ul locatiei nu poate fi null!")
    @Min(value = 1, message = "Id-ul locatiei trebuie sa fie mai mare decat 0!")
    private int locationId;

    @NotBlank(message = "Adresa nu poate fi goala!")
    @NotNull(message = "Adresa nu poate fi null!")
    private String address;

    @NotNull(message = "Id-ul tarii nu poate fi null!")
    @Range(min = 1, message = "Id-ul tarii trebuie sa fie mai mare decat 0!")
    private int countryCode;

    public LocationUpdate() {
    }

    public LocationUpdate(int locationId, String address, int countryCode) {
        this.locationId = locationId;
        this.address = address;
        this.countryCode = countryCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "LocationUpdate{" +
                "locationId=" + locationId +
                ", address='" + address + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
