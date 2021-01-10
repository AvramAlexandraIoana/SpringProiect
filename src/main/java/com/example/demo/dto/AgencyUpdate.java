package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AgencyUpdate {
    @NotNull(message = "Id-ul agentiei nu poate fi null!")
    @Range(min = 1, message = "Id-ul agentiei trebuie sa fie mai mare decat 0!")
    private int agencyId;

    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String name;

    @NotNull(message = "Id-ul locatiei nu poate fi null!")
    @Range(min = 1, message = "Id-ul locatiei trebuie sa fie mai mare decat 0!")
    private int locationCode;

    public AgencyUpdate() {
    }

    public AgencyUpdate(int agencyId, String name, int locationCode) {
        this.agencyId = agencyId;
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
        return "AgencyUpdate{" +
                "agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", locationCode=" + locationCode +
                '}';
    }
}
