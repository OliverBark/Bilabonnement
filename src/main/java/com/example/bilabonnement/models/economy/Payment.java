package com.example.bilabonnement.models.economy;

import java.sql.Date;

public class Payment {
    private final int paymentId;
    private final double amount;
    private final Date date;
    private final Type type;
    private final int rentalId;


    public Payment(int paymentId, double price, Date date, String type, int rentalId) {
        this.paymentId = paymentId;
        this.amount = price;
        this.date = date;
        this.type = Type.valueOf(type.toUpperCase());
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

    public Type getType() {
        return type;
    }

    public int getRentalId() {
        return rentalId;
    }

    public String getTypeValue(){
        if(type.equals(Type.MONTHLY)){
            return "MONTHLY";
        }
        if(type.equals(Type.ONETIME)){
            return "ONETIME";
        }
        return "null";
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", date=" + date +
                ", type=" + type +
                ", rentalId=" + rentalId +
                '}';
    }
}
