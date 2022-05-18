package com.example.bilabonnement.models.economy;

import java.sql.Date;

public class SaleRecord {
    private final int paymentId;
    private final double amount;
    private final String type;
    private final Date date;

    public SaleRecord(int payment_id, double amount, String type, Date date) {
        this.paymentId = payment_id;
        this.amount = amount;
        this.type = type;
        this.date = date;
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
