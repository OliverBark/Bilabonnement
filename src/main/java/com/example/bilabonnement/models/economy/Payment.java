package com.example.bilabonnement.models.economy;

import java.util.Date;

public class Payment {
    private final int paymentID;
    private final double amount;
    private final Date date;
    private final int subscriptionID;


    public Payment(int paymentID, double price, Date date, int subscriptionID) {
        this.paymentID = paymentID;
        this.amount = price;
        this.date = date;
        this.subscriptionID = subscriptionID;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }
}
