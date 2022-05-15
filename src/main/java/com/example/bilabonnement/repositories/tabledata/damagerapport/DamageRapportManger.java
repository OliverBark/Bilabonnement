package com.example.bilabonnement.repositories.tabledata.damagerapport;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.models.data.ActiveSubscription;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DamageRapportManger {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String primaryKey = "damage";
    private final String sections = "(damage, price)";
    private final String tableSections = "(damage varchar(45) NOT NULL, price double NOT NULL, PRIMARY KEY(damage))";

    public DamageRapport getDamageRapport(String damageRapportID){
        ArrayList<Damage> damages = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(damageRapportID, primaryKey));
            while(rs.next()){
                damages.add(new Damage(rs.getString("damage"), rs.getDouble("amount")));
            }
            return new DamageRapport(damageRapportID, damages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDamageRapport(String damageRapportID, ArrayList<Damage> damages){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createTable(damageRapportID, tableSections));
            for (int i = 0; i < damages.size(); i++) {
                stmt.executeUpdate(sqlString.createData(damageRapportID, sections, "('"+
                        damages.get(i).getDamage() + "', '" + damages.get(i).getPrice() + "')"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDamageRapport(String damageRapportID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteTable(damageRapportID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
