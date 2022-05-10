package com.example.bilabonnement.repositories.payments;

import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Payment_Manager {
    SQL_String sqlString;
    private Payment_Data data;

    public Payment getPayment(String value){
        try {
            Statement stmt;
            return generatePayment(stmt.executeQuery(sqlString.getData(data.getDatabase(), data.getPrimary_key(), value)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Payment> getPaymentList(){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Statement stmt;
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(data.getDatabase(), data.getPrimary_key()));
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
            Statement stmt;
            stmt.executeUpdate(sqlString.createData(data.getDatabase(), data.getSections(), generateValues(payment)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePayment(String value){
        try {
            Statement stmt;
            stmt.executeUpdate(sqlString.deleteData(data.getDatabase(), data.getPrimary_key(), value));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Payment generatePayment(ResultSet rs){
        try {
            return new Payment(rs.getString("payment_id"), rs.getString("reg_nr"), rs.getString("account_nr"),
                    rs.getDouble("price"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Payment payment){
        return "('" +
        payment.getPaymentID() + "', '" +
                payment.getRegNr() + "', '" +
                payment.getAccountNr() + "', '" +
                payment.getPrice() + "')";
    }
}
