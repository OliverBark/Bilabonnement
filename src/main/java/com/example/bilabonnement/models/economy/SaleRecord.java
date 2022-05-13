package com.example.bilabonnement.models.economy;

public class SaleRecord {
    private final String payment_id;
    private final double amount;
    private final String type;
    private final String date;

    public SaleRecord(String payment_id, double amount, String type, String date) {
        this.payment_id = payment_id;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
