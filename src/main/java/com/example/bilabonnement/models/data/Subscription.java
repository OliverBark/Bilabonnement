package com.example.bilabonnement.models.data;

import java.time.LocalDate;

public class Subscription {
    private final int subscriptionID;
    private final String holder;
    private final String model;
    private final String color;
    private final boolean afleveringsforsikring;
    private final boolean selvrisiko;
    private final String location;
    private final double pricePrKm;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double monthlyFee;
    private final boolean active;

    public Subscription(int subscriptionID, String holder, String model, String color, boolean afleveringsforsikring,
                        boolean selvrisiko, String location, double pricePrKm, LocalDate startDate, LocalDate endDate,
                        double monthlyFee, boolean active) {
        this.subscriptionID = subscriptionID;
        this.holder = holder;
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

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public String getHolder() {
        return holder;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public boolean isActive() {
        return active;
    }
}
