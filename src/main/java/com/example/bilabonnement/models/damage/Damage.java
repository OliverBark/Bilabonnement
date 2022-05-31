// @Author Battal Roni Özcan
package com.example.bilabonnement.models.damage;

public class Damage {
    private final int damageId;
    private final int reportId;
    private final String damage;
    private final double amount;

    public Damage(int damageId, int reportID, String damage, double price) {
        this.damageId = damageId;
        this.reportId = reportID;
        this.damage = damage;
        this.amount = price;
    }

    public int getDamageId() {
        return damageId;
    }

    public int getReportId() {
        return reportId;
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
                "damageID=" + damageId +
                ", rapportID=" + reportId +
                ", damage='" + damage + '\'' +
                ", price=" + amount +
                '}';
    }
}
