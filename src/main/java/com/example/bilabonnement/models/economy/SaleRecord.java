package com.example.bilabonnement.models.economy;

import java.sql.Date;

public class SaleRecord {
    private final int paymentId;
    private final double amount;
    private final String type;
    private final Date date;
    private final int rentalId;

    public SaleRecord(int paymentId, double amount, String type, Date date, int rentalId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.rentalId = rentalId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getRentalId() {
        return rentalId;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "payment_id=" + paymentId +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
