package com.example.bilabonnement.repositories.damage_report;

import com.example.bilabonnement.models.damage.DamageReport;
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
    private final String database = "Damage_reports";
    private final String primaryKey = "id";
    private final String sections = "(rental_id, description)";



    //Basic
    public DamageReport getDamageRapport(int id){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(id)));
            rs.next();
            return generateDamageRapport(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<DamageReport> getDamageRapportList(){
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                damageReports.add(generateDamageRapport(rs));
            }
            return damageReports;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDamageRapport(DamageReport damageReport){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(damageReport)));
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

    private DamageReport generateDamageRapport(ResultSet rs){
        try {
            return new DamageReport(rs.getInt("report_id"), rs.getInt("rental_id"), rs.getString("description"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
