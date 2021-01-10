package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PurchaseUpdate {
    @NotNull(message = "Id-ul turistului  nu poate fi null!")
    @Range(min = 1, message = "Id-ul turistului trebuie sa fie mai mare decat 0!")
    private int touristCode;

    @NotNull(message = "Id-ul excursiei  nu poate fi null!")
    @Range(min = 1, message = "Id-ul excursiei trebuie sa fie mai mare decat 0!")
    private int tripCode;

    @NotNull(message = "Data de start nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull(message = "Data de final nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull(message = "Data de achizitie nu poate fi null!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date purchaseDate;

    @NotNull(message = "Discount-ul  nu poate fi null!")
    @Range(min = 1, message = "Discount-ul trebuie sa fie mai mare decat 1!")
    private Double discount;

    public PurchaseUpdate() {
    }

    public PurchaseUpdate(int tripCode, Date startDate, Date endDate, Date purchaseDate, Double discount) {
        this.touristCode = touristCode;
        this.tripCode = tripCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchaseDate = purchaseDate;
        this.discount = discount;
    }

    public int getTouristCode() {
        return touristCode;
    }

    public void setTouristCode(int touristCode) {
        this.touristCode = touristCode;
    }

    public int getTripCode() {
        return tripCode;
    }

    public void setTripCode(int tripCode) {
        this.tripCode = tripCode;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "PurchaseRequest{" +
                "touristCode=" + touristCode +
                ", tripCode=" + tripCode +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", purchaseDate=" + purchaseDate +
                ", discount=" + discount +
                '}';
    }
}
