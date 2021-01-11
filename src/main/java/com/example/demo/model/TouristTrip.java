package com.example.demo.model;

public class TouristTrip {
    private String firstName;
    private String lastName;
    private String name;
    private Double price;
    private int numberOfSeats;
    private int duration;

    public TouristTrip() {
    }

    public TouristTrip(String firstName, String lastName, String name, Double price, int numberOfSeats, int duration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = name;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.duration = duration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String toString() {
        return "TouristTrip{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", numberOfSeats=" + numberOfSeats +
                ", duration=" + duration +
                '}';
    }
}
