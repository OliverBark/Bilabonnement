package com.example.bilabonnement.models.damage;

import java.util.ArrayList;

public class DamageRapport {
    private final String subscriptionID;
    private final ArrayList<Damage> damages;

    public DamageRapport(String subscriptionID, ArrayList<Damage> damages) {
        this.damages = damages;
        this.subscriptionID = subscriptionID;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public ArrayList<Damage> getDamages() {
        return damages;
    }
}
