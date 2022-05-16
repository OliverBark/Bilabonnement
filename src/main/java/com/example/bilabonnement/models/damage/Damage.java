package com.example.bilabonnement.models.damage;

public class Damage {
    private final int damageID;
    private final String damage;
    private final double price;

    public Damage(int damageID, String damage, double price) {
        this.damageID = damageID;
        this.damage = damage;
        this.price = price;
    }

    public int getDamageID() {
        return damageID;
    }

    public String getDamage() {
        return damage;
    }

    public double getPrice() {
        return price;
    }
}
