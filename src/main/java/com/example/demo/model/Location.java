package com.example.demo.model;

public class Location {
    private int locationId;
    private String city;
    private String address;
    private int countryCode;

    public Location() {
    }

    public Location(int locationId, String city, String address, int countryCode) {
        this.locationId = locationId;
        this.city = city;
        this.address = address;
        this.countryCode = countryCode;
    }

    public Location(String city, String address, int countryCode) {
        this.city = city;
        this.address = address;
        this.countryCode = countryCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
        return "Location{" +
                "locationId=" + locationId +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
