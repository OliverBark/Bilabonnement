package com.example.bilabonnement.repositories.costumers;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Costumer_Manager {
    SQL_String sqlString;
    private Costumer_Data data;

    public Costumer getCostumer(String value){
        try {
            Statement stmt;
            return generateCostumer(stmt.executeQuery(sqlString.getData(data.getDatabase(), data.getPrimary_key(), value)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Costumer> getCostumerList(){
        ArrayList<Costumer> costumers = new ArrayList<>();
        try {
            Statement stmt;
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(data.getDatabase(), data.getPrimary_key()));
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
            Statement stmt;
            stmt.executeUpdate(sqlString.createData(data.getDatabase(), data.getSections(), generateValues(costumer)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCostumer(String value){
        try {
            Statement stmt;
            stmt.executeUpdate(sqlString.deleteData(data.getDatabase(), data.getPrimary_key(), value));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Costumer generateCostumer(ResultSet rs){
        try {
            return new Costumer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                    rs.getString("mobile"), rs.getString("cpr_nr"), rs.getString("reg_nr"), rs.getString("account nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Costumer costumer){
        return "'" + costumer.getFirstName() + "', '" +
                costumer.getLastName() + "', '" +
                costumer.getAddress() + "', '" +
                costumer.getMobile() + "', '" +
                costumer.getCprNr() + "', '" +
                costumer.getRegNr() + "', '" +
                costumer.getAccountNr() + "')";
    }

}
