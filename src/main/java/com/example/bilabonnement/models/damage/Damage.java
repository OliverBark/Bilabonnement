package com.example.bilabonnement.models.damage;

public class Damage {
    private final int damageID;
    private final int rapportID;
    private final String price;
    private final double amount;

    public Damage(int damageID, int rapportID, String damage, double price) {
        this.damageID = damageID;
        this.rapportID = rapportID;
        this.price = damage;
        this.amount = price;
    }

    public int getDamageID() {
        return damageID;
    }

    public int getRapportID() {
        return rapportID;
    }

    public String getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damageID=" + damageID +
                ", rapportID=" + rapportID +
                ", damage='" + price + '\'' +
                ", price=" + amount +
                '}';
    }
}
