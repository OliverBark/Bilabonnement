package com.example.bilabonnement.repositories.pending_subscriptions;

import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;
import com.example.bilabonnement.repositories.subscription.SubscriptionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PendingSubscriptionsManager {
    private final String database = "PendingSubscriptions";
    private final String primaryKey = "subscription_id";
    private final String sections = "(subscription_id, model, color, afleveringsforsikring, selvrisiko, location)";
    private SubscriptionManager subscriptionManager;
    SQL_Manager sqlManager;
    SQL_String sqlString;

    public Subscription getPendingSubscription(String subscriptionID){
        return subscriptionManager.getSubscription(subscriptionID);
    }
    public ArrayList<Subscription> getPendingSubscriptionList(){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                subscriptions.add(generateSubscription(rs));
            }
            return subscriptions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSubscription(Subscription subscription){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, generateValues(subscription)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSubscription(String subscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, subscriptionID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Subscription generateSubscription(ResultSet rs){
        try {
            return new Subscription(rs.getString("subscription_id"), rs.getString("holder"), rs.getString("model"),
                    rs.getString("color"), rs.getBoolean("afleveringsforsikring"), rs.getBoolean("selvrisiko"),
                    rs.getString("location"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Subscription subscription){
        return "('" + subscription.getSubscriptionID() + "', '" +
                subscription.getHolder() + "', '" +
                subscription.getModel() + "', '" +
                subscription.getColor() + "', '" +
                subscription.isAfleveringsforsikring() + "', '" +
                subscription.isSelvrisiko() + "', '" +
                subscription.getLocation() + "')";

    }
}
