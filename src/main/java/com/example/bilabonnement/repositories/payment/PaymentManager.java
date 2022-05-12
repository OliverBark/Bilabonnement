package com.example.bilabonnement.repositories.payment;

import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentManager {
    private String database;
    private String primaryKey;
    private String sections;
    SQL_Manager sqlManager;
    SQL_String sqlString;

    public Payment getPayment(String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, paymentID));
            return generatePayment(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Payment> getCostumerList(){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, "first_name"));
            while(rs.next()){
                payments.add(generatePayment(rs));
            }
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPayment(Payment payment){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, generateValues(payment)));
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

    private Payment generatePayment(ResultSet rs){
        try {
            return new Payment(rs.getString("payment_id"), rs.getDouble("amount"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Payment payment){
        return "('" + payment.getPaymentID() + "', '" +
                payment.getPrice() + "')";

    }
}
