package com.example.bilabonnement.models.economy;

public class Payment {
    private final String paymentID;
    private final double price;
    private final String date;

    public Payment(String paymentID, double price, String date) {
        this.paymentID = paymentID;
        this.price = price;
        this.date = date;
    }

    public String getPaymentID() {
        return paymentID;
    }
    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentID='" + paymentID + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
