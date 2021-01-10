package com.example.demo.model;

public class Location {
    private int locationId;
    private String address;
    private int countryCode;

    public Location() {
    }

    public Location(int locationId, String address, int countryCode) {
        this.locationId = locationId;
        this.address = address;
        this.countryCode = countryCode;
    }

    public Location(String address, int countryCode) {
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
        return "Location{" +
                "locationId=" + locationId +
                ", address='" + address + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
