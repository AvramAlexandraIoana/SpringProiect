package com.example.demo.model;

public class TripsTop {
    private int tripId;
    private String name;
    private Double price;
    private  int numberOfSeats;
    private int duration;

    public TripsTop() {
    }

    public TripsTop(int tripId, String name, Double price, int numberOfSeats, int duration) {
        this.tripId = tripId;
        this.name = name;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.duration = duration;
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

    @Override
    public
    String toString() {
        return "TripsTop{" +
                "tripId=" + tripId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", duration=" + duration +
                '}';
    }
}
