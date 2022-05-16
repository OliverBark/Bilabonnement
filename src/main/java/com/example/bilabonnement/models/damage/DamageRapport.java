package com.example.bilabonnement.models.damage;

public class DamageRapport {
    private final int id;
    private final int subscription_id;
    private final String description;

    public DamageRapport(int id, int subscription_id, String description) {
        this.id = id;
        this.subscription_id = subscription_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getSubscription_id() {
        return subscription_id;
    }

    public String getDescription() {
        return description;
    }
}
