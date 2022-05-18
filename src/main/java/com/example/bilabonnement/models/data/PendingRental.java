package com.example.bilabonnement.models.data;

public class PendingRental {
    private final int pendingRentalId;
    private final String customerCPR;
    private final String model;
    private final String color;
    private final boolean afleveringsforsikring;
    private final boolean selvrisiko;
    private final String location;
    private final double monthlyFee;


    public PendingRental(int pendingRentalId, String customerCPR, String model, String color, boolean afleveringsforsikring,
                         boolean selvrisiko, String location, double monthlyFee) {
        this.pendingRentalId = pendingRentalId;
        this.customerCPR = customerCPR;
        this.model = model;
        this.color = color;
        this.afleveringsforsikring = afleveringsforsikring;
        this.selvrisiko = selvrisiko;
        this.location = location;
        this.monthlyFee = monthlyFee;
    }

    public int getPendingRentalId() {
        return pendingRentalId;
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

    public double getMonthlyFee() {
        return monthlyFee;
    }

    @Override
    public String toString() {
        return "PendingSubscription{" +
                "id=" + pendingRentalId +
                ", customerCPR='" + customerCPR + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", afleveringsforsikring=" + afleveringsforsikring +
                ", selvrisiko=" + selvrisiko +
                ", location='" + location + '\'' +
                ", monthlyFee=" + monthlyFee +
                '}';
    }
}

