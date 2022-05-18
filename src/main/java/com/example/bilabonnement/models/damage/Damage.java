package com.example.bilabonnement.models.damage;

public class Damage {
    private final int damageID;
    private final int reportID;
    private final String damage;
    private final double amount;

    public Damage(int damageID, int reportID, String damage, double price) {
        this.damageID = damageID;
        this.reportID = reportID;
        this.damage = damage;
        this.amount = price;
    }

    public int getDamageID() {
        return damageID;
    }

    public int getReportID() {
        return reportID;
    }

    public String getDamage() {
        return damage;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damageID=" + damageID +
                ", rapportID=" + reportID +
                ", damage='" + damage + '\'' +
                ", price=" + amount +
                '}';
    }
}
