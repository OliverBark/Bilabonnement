package com.example.bilabonnement.repositories.dataintables.salesrecords;

import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesRecordManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String database = "salesrecords";
    private final String primary_key = "payment_id";
    private final String sections = "(payment_id, amount, type, date)";

    public SaleRecord getSalesRecord(String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primary_key, paymentID));
            rs.next();
            return generateSaleRecord(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SaleRecord> getSalesRecordList(){
        ArrayList<SaleRecord> saleRecords = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primary_key));
            while(rs.next()){
                saleRecords.add(generateSaleRecord(rs));
            }
            return saleRecords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createSalesRecord(SaleRecord saleRecord){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database,sections,generateValues(saleRecord)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void SalesRecord(String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primary_key, paymentID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SaleRecord generateSaleRecord(ResultSet rs){
        try {
            return new SaleRecord(rs.getString("payment_id"), rs.getDouble("amount"),
                    rs.getString("type"), rs.getString("data"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private String generateValues(SaleRecord saleRecord){
        return "('" + saleRecord.getPayment_id() + "', '" +
                saleRecord.getAmount() + "', '" +
                saleRecord.getType() + "', '" +
                saleRecord.getDate() + "')";
    }
}
