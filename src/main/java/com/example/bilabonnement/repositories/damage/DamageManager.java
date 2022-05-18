package com.example.bilabonnement.repositories.damage;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DamageManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Damages";
    private final String primaryKey = "damage_id";
    private final String sections = "(report_id, damage, price)";



    public ArrayList<Damage> findDamages(int reportId){
        ArrayList<Damage> damages = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database,
                    "report_id", String.valueOf(reportId)));
            while(rs.next()){
                damages.add(generateDamage(rs));
            }
            return damages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //basic
    public Damage getDamage(int damageID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(damageID)));
            rs.next();
            return generateDamage(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Damage> getDamageList(){
        ArrayList<Damage> damages = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                damages.add(generateDamage(rs));
            }
            return damages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDamage(Damage damage){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(damage)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDamage(int damageID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(damageID)));
            System.out.println("stmt executed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Damage generateDamage(ResultSet rs){
        try {
            return new Damage(rs.getInt("damage_id"), rs.getInt("report_id"), rs.getString("damage"), rs.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
