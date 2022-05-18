package com.example.bilabonnement.repositories.subscription;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubscriptionManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Subscriptions";
    private final String primaryKey = "subscription_id";
    private final String sections = "(customer_cpr, model, color, afleveringsforsikring, selvrisiko, " +
            "location, price_pr_km, start_date, end_date, monthly_fee, active, monthly_fee)";

    public Rental getSubscription(int subscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(subscriptionID)));
            rs.next();
            return generateSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Rental> getSubscriptionList(){
        ArrayList<Rental> rentals = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                rentals.add(generateSubscription(rs));
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSubscription(Rental rental){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(rental)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSubscription(int subscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(subscriptionID)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Rental generateSubscription(ResultSet rs){
        try {
            return new Rental(rs.getInt("subscription_id"), rs.getString("customer_cpr"), rs.getString("model"),
                    rs.getString("color"), rs.getBoolean("afleveringsforsikring"), rs.getBoolean("selvrisiko"),
                    rs.getString("location"), rs.getDouble("price_pr_km"), rs.getDate("start_date"),
                    rs.getDate("end_date"), rs.getDouble("monthly_fee"), rs.getBoolean("active"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
