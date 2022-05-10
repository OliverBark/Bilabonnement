package com.example.bilabonnement.repositories.subscriptions;

import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Subscription_Manager {
    SQL_String sqlString;
    private Subscription_Data data;

    public Subscription getSubscription(String value){
        try {
            Statement stmt;
            return generateSubscription(stmt.executeQuery(sqlString.getData(data.getDatabase(), data.getPrimary_key(), value)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Subscription> getSubscription(){
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        try {
            Statement stmt;
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(data.getDatabase(), data.getPrimary_key()));
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
            Statement stmt;
            stmt.executeUpdate(sqlString.createData(data.getDatabase(), data.getSections(), generateValues(subscription)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSubscription(String value){
        try {
            Statement stmt;
            stmt.executeUpdate(sqlString.deleteData(data.getDatabase(), data.getPrimary_key(), value));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Subscription generateSubscription(ResultSet rs){
        try {
            return new Subscription(rs.getString("subscription_id"), rs.getString("model"), rs.getString("color"),
                    Boolean.getBoolean(rs.getString("afleveringsforsikring")), Boolean.getBoolean(rs.getString("selvrisiko")), rs.getString("location"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Subscription subscription){
        return "('" +
        subscription.getSubscriptionID() + "', '" +
                subscription.getModel() + "', '" +
                subscription.getColor() + "', '" +
                subscription.isAfleveringsforsikring() + "', '" +
                subscription.isSelvrisiko() + "', '" +
                subscription.getLocation() + "')";
    }
}
