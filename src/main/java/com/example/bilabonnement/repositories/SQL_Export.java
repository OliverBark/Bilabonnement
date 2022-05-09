package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.services.ModelService;

import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Export {
    private Statement stmt;
    private SQL_Data data;

    public SQL_Export(Statement stmt, SQL_Data data) {
        this.stmt = stmt;
        this.data = data;
    }

    //Add Entity
    public void addData(Object object){
        if(object instanceof Costumer){
            insertCostumer((Costumer) object);
        }
        if(object instanceof Subscription){
            insertSubscription((Subscription) object);
        }
        if(object instanceof Payment){
            insertPayment((Payment) object);
        }
    }
    //Services
    private void insertCostumer(Costumer costumer){
        try {
            stmt.executeUpdate("INSERT INTO * " + data.getCostumer_database() +
                    "(first_name, last_name, address, mobile, cpr_nr, reg_nr, account_nr)" +
                    " VALUES('" +
                    costumer.getFirstName() + "', '" +
                    costumer.getLastName() + "', '" +
                    costumer.getAddress() + "', '" +
                    costumer.getMobile() + "', '" +
                    costumer.getCprNr() + "', '" +
                    costumer.getRegNr() + "', '" +
                    costumer.getAccountNr() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insertSubscription(Subscription subscription){
        try {
            stmt.executeUpdate("INSERT INTO * " + data.getSubscription_database() +
                    "(subscription_id, model, color, afleveringsforsikring, selvrisiko, location)" +
                    " VALUES('" +
                    subscription.getSubscriptionID() + "', '" +
                    subscription.getModel() + "', '" +
                    subscription.getColor() + "', '" +
                    subscription.isAfleveringsforsikring() + "', '" +
                    subscription.isSelvrisiko() + "', '" +
                    subscription.getLocation() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private void insertPayment(Payment payment){
        try {
            stmt.executeUpdate("INSERT INTO * " + data.getPayment_database() +
                    "(payment_id, reg_nr, account_nr, price)" +
                    " VALUES('" +
                    payment.getPaymentID() + "', '" +
                    payment.getRegNr() + "', '" +
                    payment.getAccountNr() + "', '" +
                    payment.getPrice() + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
