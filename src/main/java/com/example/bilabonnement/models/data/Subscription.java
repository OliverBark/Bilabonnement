package com.example.bilabonnement.models.data;

public class Subscription {
    private final String subscriptionID;
    private final String model;
    private final String color;
    private final boolean afleveringsforsikring;
    private final boolean selvrisiko;
    private final String location;

    public Subscription(String subscriptionID, String model, String color, boolean afleveringsforsikring, boolean selvrisiko, String location){
        this.subscriptionID = subscriptionID;
        this.model = model;
        this.color = color;
        this.afleveringsforsikring = afleveringsforsikring;
        this.selvrisiko = selvrisiko;
        this.location = location;
    }

    public String getSubscriptionID() {
        return subscriptionID;
    }

    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public boolean isAfleveringsforsikring() {
        return afleveringsforsikring;
    }
    public boolean isSelvrisiko() {
        return selvrisiko;
    }
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", afleveringsforsikring=" + afleveringsforsikring +
                ", selvrisiko=" + selvrisiko +
                '}';
    }
}
