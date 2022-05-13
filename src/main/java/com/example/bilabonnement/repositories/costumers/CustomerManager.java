package com.example.bilabonnement.repositories.costumers;

import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    private final String database = "customers";
    private final String primary_key = "cpr_nr";
    private final String sections = "(first_name, last_name, address, mobile, cpr_nr, reg_nr, account_nr)";

    public Customer getCustomer(String cprNr){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primary_key, cprNr));
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
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primary_key));
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
            stmt.executeUpdate(sqlString.createData(database,sections,generateValues(customer)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomer(String cprNr){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primary_key, cprNr));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Customer generateCustomer(ResultSet rs){
        try {
            return new Customer(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"),
                    rs.getString("mobile"), rs.getString("cpr_nr"), rs.getString("reg_nr"), rs.getString("account_nr"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateValues(Customer customer){
        return "('" + customer.getFirstName() + "', '" +
                customer.getLastName() + "', '" +
                customer.getAddress() + "', '" +
                customer.getMobile() + "', '" +
                customer.getCprNr() + "', '" +
                customer.getRegNr() + "', '" +
                customer.getAccountNr() + "')";
    }

    public static void main(String[] args) {
        CustomerManager customerManager = new CustomerManager();
        System.out.println(customerManager.getCustomer("2000"));
    }
}
