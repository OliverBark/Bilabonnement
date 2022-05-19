package com.example.bilabonnement.repositories.rental;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RentalManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Rentals";
    private final String primaryKey = "rental_id";
    private final String sections = "(customer_cpr, model, color, afleveringsforsikring, selvrisiko, " +
            "location, price_pr_km, start_date, end_date, monthly_fee, active, monthly_fee)";

    public Rental getRental(int rentalId){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(rentalId)));
            rs.next();
            return generateSubscription(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Rental> getRentalList(){
        ArrayList<Rental> rentals = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                rentals.add(generateSubscription(rs));
            }
            return rentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createRental(Rental rental){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(rental)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteRental(int rentalId){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(rentalId)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateRental(int rentalId, String field, String newValue){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.updateData(database, field, newValue, primaryKey, String.valueOf(rentalId)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Rental generateSubscription(ResultSet rs){
        try {
            return new Rental(rs.getInt("rental_id"), rs.getString("customer_cpr"), rs.getString("model"),
                    rs.getString("color"), rs.getBoolean("afleveringsforsikring"), rs.getBoolean("selvrisiko"),
                    rs.getString("location"), rs.getDouble("price_pr_km"), rs.getDate("start_date"),
                    rs.getDate("end_date"), rs.getDouble("monthly_fee"), rs.getBoolean("active"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
