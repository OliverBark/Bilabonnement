package com.example.bilabonnement.models.economy;

public class Payment {
    private final String paymentID;
    private final String regNr;
    private final String accountNr;
    private final double price;

    public Payment(String paymentID, String regNr, String accountNr, double price) {
        this.paymentID = paymentID;
        this.regNr = regNr;
        this.accountNr = accountNr;
        this.price = price;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getRegNr() {
        return regNr;
    }

    public String getAccountNr() {
        return accountNr;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "regNr='" + regNr + '\'' +
                ", accountNr='" + accountNr + '\'' +
                ", price=" + price +
                '}';
    }


}
