package com.example.bilabonnement.repositories.sale_record;

import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SaleRecordManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Sale_records";
    private final String primaryKey = "payment_id";
    private final String sections = "(amount, type, date)";

    public SaleRecord getSaleRecord(int paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(paymentID)));
            rs.next();
            return generateSaleRecord(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SaleRecord> getSaleRecordList(){
        ArrayList<SaleRecord> saleRecords = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                saleRecords.add(generateSaleRecord(rs));
            }
            return saleRecords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSaleRecord(SaleRecord saleRecord){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(saleRecord)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteSaleRecord(int paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(paymentID)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SaleRecord generateSaleRecord(ResultSet rs){
        try {
            return new SaleRecord(rs.getInt("payment_id"), rs.getDouble("amount"), rs.getString("type"),
                    rs.getDate("date"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
