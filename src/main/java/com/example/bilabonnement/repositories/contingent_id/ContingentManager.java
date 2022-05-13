package com.example.bilabonnement.repositories.contingent_id;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.economy.Contingent;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;
import com.example.bilabonnement.repositories.payment.PaymentManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContingentManager {
    private String database(Costumer costumer){
        return "contingent_" + costumer.getCprNr();
    }
    private final String primaryKey = "payment_id";
    private final String tableSections = "('payment_id' varchar(45) NOT NULL)";
    private PaymentManager paymentManager;
    SQL_Manager sqlManager;
    SQL_String sqlString;

    public Contingent getContingent(Costumer costumer){
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database(costumer), primaryKey));
            while(rs.next()){
                payments.add(paymentManager.getPayment(rs.getString("payment_id")));
            }
            return new Contingent(database(costumer), payments);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createContingent(Costumer costumer){
        try {
            Statement stmt = sqlManager.establishConnection();
            try {
                stmt.executeUpdate(sqlString.deleteTable(database(costumer)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stmt.executeUpdate(sqlString.createTable(database(costumer), tableSections));
        } catch (RuntimeException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteContingentData(Costumer costumer, String paymentID){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database(costumer), primaryKey, paymentID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteContingentList(Costumer costumer){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteTable(database(costumer)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
