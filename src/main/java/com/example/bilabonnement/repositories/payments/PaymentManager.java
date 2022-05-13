package com.example.bilabonnement.repositories.payments;

import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String database = "payments";
    private final String primary_key = "payment_id";
    private final String sections = "(payment_id, price, date)";

    public Payment getPayment(String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primary_key, paymentID));
            rs.next();
            return generatePayment(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Payment> getPaymentList(){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primary_key));
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
            stmt.executeUpdate(sqlString.createData(database,sections,generateValues(payment)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePayment(String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primary_key, paymentID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Payment generatePayment(ResultSet rs){
        try {
            return new Payment(rs.getString("payment_id"), rs.getDouble("price"), rs.getString("date"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private String generateValues(Payment payment){
        return "('" + payment.getPaymentID() + "', '" +
                payment.getPrice() + "', '" +
                payment.getDate() + "')";
    }
}
