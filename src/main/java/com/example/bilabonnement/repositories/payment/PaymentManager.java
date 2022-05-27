package com.example.bilabonnement.repositories.payment;

import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;
import com.example.bilabonnement.repositories.sale_record.SaleRecordManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Payments";
    private final String primaryKey = "payment_id";
    private final String sections = "(amount, date, type,  rental_id)";

    public boolean alreadyExistsPayment(Payment payment) {
        ArrayList<Payment> payments = getPaymentList();
        for (Payment tempPayment : payments) {
            if (tempPayment.getRentalId() == payment.getRentalId() &&
                    tempPayment.getType() == payment.getType() &&
                    tempPayment.getDate().equals(payment.getDate())) {
                return true;
            }
        }
        return false;
    }

    public Payment getPayment(int paymentID) {
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(paymentID)));
            rs.next();
            return generatePayment(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Payment> getPaymentList() {
        ArrayList<Payment> payments = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while (rs.next()) {
                payments.add(generatePayment(rs));
            }
            return payments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPayment(Payment payment) {
        try {
            Statement stmt = sqlManager.establishConnection();
            System.out.println("establieshed");
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(payment)));
            System.out.println("updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePayment(int paymentID) {
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(paymentID)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePayment(int paymentId, String field, String newValue) {
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.updateData(database, field, newValue, primaryKey, String.valueOf(paymentId)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Payment generatePayment(ResultSet rs) {
        try {
            return new Payment(rs.getInt("payment_id"), rs.getDouble("amount"),
                    rs.getDate("date"), rs.getString("type"), rs.getInt("rental_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
