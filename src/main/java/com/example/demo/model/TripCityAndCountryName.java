package com.example.demo.model;

public class TripCityAndCountryName {
    private String tripName;
    private Double tripPrice;
    private String city;
    private String country;

    public TripCityAndCountryName() {
    }

    public TripCityAndCountryName(String tripName, Double tripPrice, String city, String country) {
        this.tripName = tripName;
        this.tripPrice = tripPrice;
        this.city = city;
        this.country = country;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public Double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(Double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public
    String toString() {
        return "TripCityAndCountryName{" +
                "tripName='" + tripName + '\'' +
                ", tripPrice=" + tripPrice +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
