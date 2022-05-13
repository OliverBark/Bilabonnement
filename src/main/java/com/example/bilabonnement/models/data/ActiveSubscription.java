package com.example.bilabonnement.models.data;

import com.example.bilabonnement.models.damage.Damage;

import java.util.ArrayList;

public class ActiveSubscription {
    private final String activeSubscriptionID;
    private final String subscriptionID;
    private final double price_pr_km;
    private final ArrayList<Damage> damagePrices;
    private final String startDate;
    private final String endDate;

    public ActiveSubscription(String activeSubscriptionID, String subscriptionID, double price_pr_km,
                              ArrayList<Damage> damagePrices, String startDate, String endDate) {
        this.activeSubscriptionID = activeSubscriptionID;
        this.subscriptionID = subscriptionID;
        this.price_pr_km = price_pr_km;
        this.damagePrices = damagePrices;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getActiveSubscriptionID() {
        return activeSubscriptionID;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public double getPrice_pr_km() {
        return price_pr_km;
    }

    public ArrayList<Damage> getDamagePrices() {
        return damagePrices;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
