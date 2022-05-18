package com.example.bilabonnement.repositories.sales_record;

import com.example.bilabonnement.models.economy.SalesRecord;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesRecordManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Sales_records";
    private final String primaryKey = "payment_id";
    private final String sections = "(amount, type, date)";

    public SalesRecord getSalesRecord(int paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(paymentID)));
            rs.next();
            return generateSalesRecord(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SalesRecord> getSalesRecordList(){
        ArrayList<SalesRecord> salesRecords = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                salesRecords.add(generateSalesRecord(rs));
            }
            return salesRecords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSalesRecord(SalesRecord salesRecord){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(salesRecord)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSalesRecord(int paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(paymentID)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SalesRecord generateSalesRecord(ResultSet rs){
        try {
            return new SalesRecord(rs.getInt("payment_id"), rs.getDouble("amount"), rs.getString("type"),
                    rs.getDate("date"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}