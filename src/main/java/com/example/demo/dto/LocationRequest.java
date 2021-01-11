package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocationRequest {
    @NotBlank(message = "Orasul nu poate fi gol!")
    @NotNull(message = "Orasul nu poate fi null!")
    private String city;

    @NotBlank(message = "Adresa nu poate fi goala!")
    @NotNull(message = "Adresa nu poate fi null!")
    private String address;

    @NotNull(message = "Id-ul tarii nu poate fi null!")
    @Range(min = 1, message = "Id-ul tarii trebuie sa fie mai mare decat 0!")
    private int countryCode;

    public LocationRequest() {
    }

    public LocationRequest(String city, String address, int countryCode) {
        this.city = city;
        this.address = address;
        this.countryCode = countryCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
    public
    String toString() {
        return "LocationRequest{" +
                "city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
