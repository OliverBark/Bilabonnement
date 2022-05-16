package com.example.bilabonnement.models.economy;

import java.time.LocalDate;

public class Payment {
    private final int paymentID;
    private final double price;
    private final LocalDate date;
    private final int subscriptionID;


    public Payment(int paymentID, double price, LocalDate date, int subscriptionID) {
        this.paymentID = paymentID;
        this.price = price;
        this.date = date;
        this.subscriptionID = subscriptionID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }
}
