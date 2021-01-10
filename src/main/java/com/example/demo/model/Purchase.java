package com.example.demo.model;

import java.util.Date;

public class Purchase {
    private int touristCode;
    private int tripCode;
    private Date startDate;
    private Date endDate;
    private Date purchaseDate;
    private Double discount;

    public Purchase() {
    }

    public Purchase(int touristCode, int tripCode, Date startDate, Date endDate, Date purchaseDate, Double discount) {
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
        return "Purchase{" +
                "touristCode=" + touristCode +
                ", tripCode=" + tripCode +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", purchaseDate=" + purchaseDate +
                ", discount=" + discount +
                '}';
    }
}
