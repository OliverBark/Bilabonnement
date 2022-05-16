package com.example.bilabonnement.repositories.damage_rapport;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DamageRapportManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Damage_rapport";
    private final String primaryKey = "id";
    private final String sections = "(subscription_id, description)";



    //Basic
    public DamageRapport getDamageRapport(int id){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(id)));
            rs.next();
            return generateDamageRapport(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<DamageRapport> getDamageRapportList(){
        ArrayList<DamageRapport> damageRapports = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                damageRapports.add(generateDamageRapport(rs));
            }
            return damageRapports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDamageRapport(DamageRapport damageRapport){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(damageRapport)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDamage(int id){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(id)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DamageRapport generateDamageRapport(ResultSet rs){
        try {
            return new DamageRapport(rs.getInt("id"), rs.getInt("subscription_id"), rs.getString("description"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
