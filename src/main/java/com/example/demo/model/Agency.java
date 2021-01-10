package com.example.demo.model;

public class Agency {
    private int agencyId;
    private String name;
    private int locationCode;

    public Agency() {
    }

    public Agency(int agencyId, String name, int locationCode) {
        this.agencyId = agencyId;
        this.name = name;
        this.locationCode = locationCode;
    }

    public Agency(String name, int locationCode) {
        this.name = name;
        this.locationCode = locationCode;
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

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", locationCode=" + locationCode +
                '}';
    }
}
