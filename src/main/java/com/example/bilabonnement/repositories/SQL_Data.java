package com.example.bilabonnement.repositories;

public class SQL_Data {
    //User
    private String current_user;

    //Costumer
    private String costumer_database = "costumers";
    private String costumer_primary_key = "cpr_nr";
    //Subscription
    private String subscription_database = "subscriptions";
    private String subscription_primary_key = "subscription_id";
    //Payment
    private String payment_database = "payments";
    private String payment_primary_key = "payment_id";
    //User
    private String user_database = "users";
    private String user_primary_key = "username";

    public SQL_Data(String current_user){
        this.current_user = current_user;
    }

    public String getCurrent_user() {
        return current_user;
    }

    public String getCostumer_database() {
        return costumer_database;
    }

    public String getCostumer_primary_key() {
        return costumer_primary_key;
    }

    public String getSubscription_database() {
        return subscription_database;
    }

    public String getSubscription_primary_key() {
        return subscription_primary_key;
    }

    public String getPayment_database() {
        return payment_database;
    }

    public String getPayment_primary_key() {
        return payment_primary_key;
    }

    public String getUser_database() {
        return user_database;
    }

    public String getUser_primary_key() {
        return user_primary_key;
    }
}
