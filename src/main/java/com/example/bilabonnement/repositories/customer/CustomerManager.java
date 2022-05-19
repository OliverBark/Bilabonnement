package com.example.bilabonnement.repositories.customer;

import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Customers";
    private final String primaryKey = "cpr_nr";
    private final String sections = "(first_name, last_name, address, mobile, cpr_nr, reg_nr, account_nr)";

    public Customer getCustomer(String cprNr){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, cprNr));
            rs.next();
            return generateCustomer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Customer> getCustomerList(){
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                customers.add(generateCustomer(rs));
            }
            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createCustomer(Customer customer){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(customer)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomer(String cprNr){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(cprNr)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCustomer(String cprNr, String field, String newValue){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.updateData(database, field, newValue, primaryKey, String.valueOf(cprNr)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Customer generateCustomer(ResultSet rs){
        try {
            return new Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                    rs.getString("mobile"), rs.getString("cpr_nr"), rs.getInt("reg_nr"), rs.getInt("account_nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
