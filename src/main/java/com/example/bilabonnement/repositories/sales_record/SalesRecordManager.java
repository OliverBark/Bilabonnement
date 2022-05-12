package com.example.bilabonnement.repositories.sales_record;

import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesRecordManager {
    SQL_Manager sqlManager;
    SQL_String sqlString;
    private String database;
    private String primaryKey;
    private String sections;


    public SaleRecord getSalesRecord(String value){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, value));
            return generateSalesRecord(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SaleRecord> getSalesRecordList(){
        ArrayList<SaleRecord> records = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, "first_name"));
            while(rs.next()){
                records.add(generateSalesRecord(rs));
            }
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSalesRecord(SaleRecord saleRecord){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, generateValues(saleRecord)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSalesRecord(String value){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, value));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SaleRecord generateSalesRecord(ResultSet rs){
        try {
            return new SaleRecord(rs.getString("payment_id"), rs.getDouble("amount"), rs.getString("type"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(SaleRecord saleRecord){
        return "('" + saleRecord.getPayment_id() + "', '" +
                saleRecord.getAmount() + "', '" +
                saleRecord.getType() + "')";

    }
}
