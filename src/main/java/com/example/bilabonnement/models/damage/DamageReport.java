package com.example.bilabonnement.models.damage;

public class DamageReport {
    private final int id;
    private final int rental_id;
    private final String description;

    public DamageReport(int id, int subscription_id, String description) {
        this.id = id;
        this.rental_id = subscription_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getRental_id() {
        return rental_id;
    }

    public String getDescription() {
        return description;
    }
}
