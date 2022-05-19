package com.example.bilabonnement.models.economy;

import java.sql.Date;

public class Payment {
    private final int paymentId;
    private final double amount;
    private final Date date;
    private final int rentalId;


    public Payment(int paymentId, double price, Date date, int rentalId) {
        this.paymentId = paymentId;
        this.amount = price;
        this.date = date;
        this.rentalId = rentalId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public int getRentalId() {
        return rentalId;
    }
}
