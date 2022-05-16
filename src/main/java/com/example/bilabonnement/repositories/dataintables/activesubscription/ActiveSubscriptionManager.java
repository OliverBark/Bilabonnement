package com.example.bilabonnement.repositories.dataintables.activesubscription;

import com.example.bilabonnement.models.data.ActiveSubscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;
import com.example.bilabonnement.repositories.tabledata.damagerapport.DamageRapportManger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ActiveSubscriptionManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String database = "activesubscriptions";
    private final String primary_key = "active_subscription_id";
    private final String sections = "(active_subscription_id, subscription_id, price_pr_km, damage_prices, start_date, end_date, monthly_fee)";

    public ActiveSubscription getActiveSubscription(String activeSubscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database,primary_key,activeSubscriptionID));
            rs.next();
            return generateActiveSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ActiveSubscription> getActiveSubscriptionList(){
        ArrayList<ActiveSubscription> activeSubscriptions = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primary_key));
            while(rs.next()){
                activeSubscriptions.add(generateActiveSubscription(rs));
            }
            return activeSubscriptions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createActiveSubscription(ActiveSubscription activeSubscription){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, generateValues(activeSubscription)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteActiveSubscription(String activeSubscriptionID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primary_key, activeSubscriptionID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ActiveSubscription generateActiveSubscription(ResultSet rs){
        try {
            DamageRapportManger damageRapportManger = new DamageRapportManger();
            return new ActiveSubscription(rs.getString("active_subscription_id"), rs.getString("subscription_id"),
                    rs.getDouble("price_pr_km"), damageRapportManger.getDamageRapport(rs.getString("damage_prices")).getDamages(),
                    rs.getString("start_date"), rs.getString("end_date"), rs.getDouble("monthly_fee"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(ActiveSubscription activeSubscription){
        return "('" + activeSubscription.getActiveSubscriptionID() + "', '" +
                activeSubscription.getSubscriptionID() + "', '" +
                activeSubscription.getPrice_pr_km() + "', '" +
                activeSubscription.getDamagePrices() + "', '" +
                activeSubscription.getStartDate() + "', '" +
                activeSubscription.getEndDate() + "', '" +
                activeSubscription.getMonthly_Fee() + "')";

    }
}
