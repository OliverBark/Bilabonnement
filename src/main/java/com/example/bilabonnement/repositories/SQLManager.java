package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;

import java.sql.*;
import java.util.ArrayList;

public class SQLManager {
    private SQL_DATAHANDLING dataHand;
    //Start
    public SQLManager(String user, String password){
        String url = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dataHand = new SQL_DATAHANDLING(stmt);
        } catch (SQLException e) {
            System.out.println("Failure in: establishConnection");
        }
    }

    //Single Entities
    public Costumer getCostumer(){
    }
    public Subscription getSubscription(){
    }
    public Payment getPayment(){
    }
    //ArrayLists
    public ArrayList<Costumer> getCostumerList(){
        return dataHand.getCostumerData();
    }
    public ArrayList<Subscription> getSubscriptonList(){}
    public ArrayList<Payment> getPaymentList(){}



}
