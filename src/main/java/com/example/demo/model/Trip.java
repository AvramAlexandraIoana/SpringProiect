package com.example.demo.model;

public class Trip {
    private int tripId;
    private String name;
    private Double price;
    private int numberOfSeats;
    private int duration;
    private int agencyId;
    private int locationId;

    public Trip() {
    }

    public Trip(int tripId, String name, Double price, int numberOfSeats, int duration, int agencyId, int locationId) {
        this.tripId = tripId;
        this.name = name;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.duration = duration;
        this.agencyId = agencyId;
        this.locationId = locationId;
    }

    public Trip(String name, Double price, int numberOfSeats, int duration, int agencyId, int locationId) {
        this.name = name;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.duration = duration;
        this.agencyId = agencyId;
        this.locationId = locationId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", duration=" + duration +
                ", agencyId=" + agencyId +
                ", locationId=" + locationId +
                '}';
    }
}
