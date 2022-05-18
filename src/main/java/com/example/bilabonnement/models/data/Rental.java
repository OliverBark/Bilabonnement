package com.example.bilabonnement.models.data;

import java.util.Date;

public class Rental {
    private final int rentalId;
    private final String customerCPR;
    private final String model;
    private final String color;
    private final boolean afleveringsforsikring;
    private final boolean selvrisiko;
    private final String location;
    private final double pricePrKm;
    private final Date startDate;
    private final Date endDate;
    private final double monthlyFee;
    private final boolean active;

    public Rental(int rentalId, String holder, String model, String color, boolean afleveringsforsikring,
                  boolean selvrisiko, String location, double pricePrKm, Date startDate, Date endDate,
                  double monthlyFee, boolean active) {
        this.rentalId = rentalId;
        this.customerCPR = holder;
        this.model = model;
        this.color = color;
        this.afleveringsforsikring = afleveringsforsikring;
        this.selvrisiko = selvrisiko;
        this.location = location;
        this.pricePrKm = pricePrKm;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyFee = monthlyFee;
        this.active = active;
    }

    public int getRentalId() {
        return rentalId;
    }

    public String getCustomerCPR() {
        return customerCPR;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public boolean isAfleveringsforsikring() {
        return afleveringsforsikring;
    }

    public boolean isSelvrisiko() {
        return selvrisiko;
    }

    public String getLocation() {
        return location;
    }

    public double getPricePrKm() {
        return pricePrKm;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public boolean isActive() {
        return active;
    }
}
