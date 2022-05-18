package com.example.bilabonnement.models.damage;

public class DamageReport {
    private final int report_id;
    private final int rental_id;
    private final String description;

    public DamageReport(int id, int subscription_id, String description) {
        this.report_id = id;
        this.rental_id = subscription_id;
        this.description = description;
    }

    public int getReport_id() {
        return report_id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public String getDescription() {
        return description;
    }
}
