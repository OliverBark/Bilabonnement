package com.example.bilabonnement.repositories.costumer;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CostumerManager {
    SQL_Manager sqlManager;
    SQL_String sqlString;
    private final String database = "Costumers";
    private final String primaryKey = "cpr_nr";
    private final String sections = "(first_name, last_name, address, mobile, cpr_nr, reg_nr, account_nr)";


    public Costumer getCostumer(String cpr){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, cpr));
            return generateCostumer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Costumer> getCostumerList(){
        ArrayList<Costumer> costumers = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, "first_name"));
            while(rs.next()){
                costumers.add(generateCostumer(rs));
            }
            return costumers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createCostumer(Costumer costumer){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, generateValues(costumer)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCostumer(String cpr){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, cpr));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Costumer generateCostumer(ResultSet rs){
        try {
            return new Costumer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                    rs.getString("mobile"), rs.getString("cpr_nr"), rs.getString("reg_nr"), rs.getString("account_nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Costumer costumer){
        return "('" + costumer.getFirstName() + "', '" +
                costumer.getLastName() + "', '" +
                costumer.getAddress() + "', '" +
                costumer.getMobile() + "', '" +
                costumer.getCprNr() + "', '" +
                costumer.getRegNr() + "', '" +
                costumer.getAccountNr() + "')";

    }
}
