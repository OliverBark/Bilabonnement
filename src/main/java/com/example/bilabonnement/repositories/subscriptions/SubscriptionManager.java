package com.example.bilabonnement.repositories.subscriptions;

import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;
import com.example.bilabonnement.repositories.costumers.CustomerManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubscriptionManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String database = "subscriptions";
    private final String primary_key = "subscription_id";
    private final String sections = "(subscription_id, holder, model, color, afleveringsforsikring, selvrisiko, location)";

    public Subscription getSubscription(String subscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primary_key, subscriptionID));
            rs.next();
            return generateSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Subscription> getSubscriptionList(){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primary_key));
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSubscription(String subscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primary_key, subscriptionID));
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
