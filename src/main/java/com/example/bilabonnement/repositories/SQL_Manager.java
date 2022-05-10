package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.data.Costumer;
import com.example.bilabonnement.models.data.Subscription;
import com.example.bilabonnement.models.economy.Payment;

import java.sql.*;
import java.util.ArrayList;

public class SQL_Manager {
    private SQL_Import sqlImport;
    private SQL_Export sqlExport;
    private SQL_Update sqlUpdate;

    //Start
    public SQL_Manager(){
        String url = "";
        String user = "";
        String password = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            SQL_Data data = new SQL_Data(user);
            sqlImport = new SQL_Import(stmt, data);
            sqlExport = new SQL_Export(stmt, data);
        } catch (SQLException e) {
            System.out.println("Failure in: establishConnection");
        }
    }

    //Single Object
    public Costumer getCostumer(String value){
        return sqlImport.getCostumer(value);
    }
    public Subscription getSubscription(String value){
        return sqlImport.getSubscription(value);
    }
    public Payment getPayment(String value){
        return sqlImport.getPayment(value);
    }

    //ArrayLists
    public ArrayList<Costumer> getCostumerList(){
        return sqlImport.getCostumerList();
    }
    public ArrayList<Subscription> getSubscriptionList(){
        return sqlImport.getSubscriptionList();
    }
    public ArrayList<Payment> getPaymentList(){
        return sqlImport.getPaymentList();
    }

    //Add Object
    public void addData(Object object){
        sqlExport.addData(object);
    }

    //Remove Object
    public void deleteData(Object object){
        sqlUpdate.deleteData(object);
    }

    //Update Object
    public void updateData(Object object){
        deleteData(object);
        addData(object);
    }


}
