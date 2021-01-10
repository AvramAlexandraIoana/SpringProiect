package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TouristUpdate {
    @NotNull(message = "Id-ul turistului nu poate fi null!")
    @Min(value = 1, message = "Id-ul turistului trebuie sa fie mai mare decat 0!")
    private int touristId;

    @NotBlank(message = "Prenumele nu poate fi gol!")
    @NotNull(message = "Prenumele nu poate fi null!")
    private String firstName;

    @NotBlank(message = "Numele nu poate fi gol!")
    @NotNull(message = "Numele nu poate fi null!")
    private String lastName;

    @NotNull(message = "Data nasterii nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    public TouristUpdate() {
    }

    public TouristUpdate(int touristId, String firstName, String lastName, Date dateOfBirth) {
        this.touristId = touristId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getTouristId() {
        return touristId;
    }

    public void setTouristId(int touristId) {
        this.touristId = touristId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "TouristUpdate{" +
                "touristId=" + touristId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
