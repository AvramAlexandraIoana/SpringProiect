package com.example.demo.model;

public class TouristNumberOfTrips {
    private String firstName;
    private String lastName;
    private int tripCount;

    public TouristNumberOfTrips() {
    }

    public TouristNumberOfTrips(String firstName, String lastName, int tripCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tripCount = tripCount;
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

    public int getTripCount() {
        return tripCount;
    }

    public void setTripCount(int tripCount) {
        this.tripCount = tripCount;
    }

    @Override
    public String toString() {
        return "TouristNumberOfTrips{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tripCount=" + tripCount +
                '}';
    }
}
