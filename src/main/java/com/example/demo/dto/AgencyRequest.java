package com.example.demo.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AgencyRequest {
    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String name;

    @NotNull(message = "Id-ul locatiei nu poate fi null!")
    @Range(min = 1, message = "Id-ul locatiei trebuie sa fie mai mare decat 0!")
    private int locationCode;

    public AgencyRequest() {
    }

    public AgencyRequest(String name, int locationCode) {
        this.name = name;
        this.locationCode = locationCode;
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
        return "AgencyRequest{" +
                "name='" + name + '\'' +
                ", locationCode=" + locationCode +
                '}';
    }
}
