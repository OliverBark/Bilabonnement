package com.example.bilabonnement.models.damage;

public class DamageRapport {
    private final int id;
    private final int subscription_id;

    public DamageRapport(int id, int subscription_id) {
        this.id = id;
        this.subscription_id = subscription_id;
    }

    public int getId() {
        return id;
    }

    public int getSubscription_id() {
        return subscription_id;
    }
}
