package com.example.bilabonnement.models.damage;

public class Damage {
    private final String damage;
    private final double price;

    public Damage(String damage, double price) {
        this.damage = damage;
        this.price = price;
    }

    public String getDamage() {
        return damage;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damage='" + damage + '\'' +
                ", price=" + price +
                '}';
    }
}
