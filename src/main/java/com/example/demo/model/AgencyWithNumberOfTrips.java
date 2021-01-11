package com.example.demo.model;

public class AgencyWithNumberOfTrips {
    private int agencyId;
    private String name;
    private int tripCount;

    public AgencyWithNumberOfTrips() {
    }

    public AgencyWithNumberOfTrips(int agencyId, String name, int tripCount) {
        this.agencyId = agencyId;
        this.name = name;
        this.tripCount = tripCount;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTripCount() {
        return tripCount;
    }

    public void setTripCount(int tripCount) {
        this.tripCount = tripCount;
    }

    @Override
    public
    String toString() {
        return "AgencyWithNumberOfTrips{" +
                "agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", tripCount=" + tripCount +
                '}';
    }
}
