package com.example.bilabonnement.models.economy;

public class Payment {
    private final String paymentID;
    private final double price;

    public Payment(String paymentID, double price) {
        this.paymentID = paymentID;
        this.price = price;
    }

    public String getPaymentID() {
        return paymentID;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Payment{" +
                ", price=" + price +
                '}';
    }


}
