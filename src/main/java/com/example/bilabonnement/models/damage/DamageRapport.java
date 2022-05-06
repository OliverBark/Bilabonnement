package com.example.bilabonnement.models.damage;

import java.util.ArrayList;

public class DamageRapport {
    private final String subscriptionID;
    private ArrayList<Damage> damages;

    public DamageRapport(String subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public ArrayList<Damage> getDamages() {
        return damages;
    }
    public void setDamages(ArrayList<Damage> damages) {
        this.damages = damages;
    }
}
