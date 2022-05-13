package com.example.bilabonnement.repositories.damage_rapport_id;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DamageRapportManager {
    private String database(Subscription subscription){
        return "contingent_" + subscription.getSubscriptionID();
    }
    private final String primaryKey = "DamageRapport_id";
    private final String tableSections = "(damage, price)";
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();

    public DamageRapport getDamageRapport(Subscription subscription){
        ArrayList<Damage> damages = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database(subscription), primaryKey));
            while(rs.next()){
                damages.add(new Damage(rs.getString("damage"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return new DamageRapport(database(subscription), damages);
    }
    public void createDamageRapport(Subscription subscription){
        try {
            Statement stmt = sqlManager.establishConnection();
            try {
                stmt.executeUpdate(sqlString.deleteTable(database(subscription)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate(sqlString.createTable(database(subscription), tableSections));
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDamageRapportData(Subscription subscription, String damage){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database(subscription), primaryKey, damage));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDamageRapportList(Subscription subscription){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteTable(database(subscription)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
