package com.example.bilabonnement.repositories.pending_subscription;

import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.models.data.PendingSubscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PendingSubscriptionManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Pending_subscriptions";
    private final String primaryKey = "id";
    private final String sections = "(customer_cpr, model, color, afleveringsforsikring, selvrisiko, " +
        "location, monthly_fee)";

    public PendingSubscription getPendingSubscription(int id){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(id)));
            rs.next();
            return generatePendingSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<PendingSubscription> getPendingSubscriptionList(){
        ArrayList<PendingSubscription> pendingSubscriptions = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                pendingSubscriptions.add(generatePendingSubscription(rs));
            }
            return pendingSubscriptions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPendingSubscription(PendingSubscription pendingSubscription){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(pendingSubscription)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePendingSubscription(int id){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(id)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PendingSubscription generatePendingSubscription(ResultSet rs){
        try {
            return new PendingSubscription(rs.getInt("id"), rs.getString("customer_cpr"), rs.getString("model"),
                    rs.getString("color"), rs.getBoolean("afleveringsforsikring"), rs.getBoolean("selvrisiko"),
                    rs.getString("location"), rs.getDouble("monthly_fee"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
