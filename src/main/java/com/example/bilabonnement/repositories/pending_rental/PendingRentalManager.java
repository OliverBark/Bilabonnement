package com.example.bilabonnement.repositories.pending_rental;

import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PendingRentalManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Pending_rentals";
    private final String primaryKey = "pending_rental_id";
    private final String sections = "(cpr_nr, car_model, color, afleveringsforsikring, selvrisiko, " +
        "location, monthly_fee)";

    public PendingRental getPendingRental(int pendingRentalId){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, String.valueOf(pendingRentalId)));
            rs.next();
            return generatePendingRental(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<PendingRental> getPendingRentalList(){
        ArrayList<PendingRental> pendingRentals = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                pendingRentals.add(generatePendingRental(rs));
            }
            return pendingRentals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createPendingRental(PendingRental pendingRental){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(pendingRental)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletePendingRental(int pendingRentalId){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(pendingRentalId)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatePendingRental(int pendingRentalId, String field, String newValue){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.updateData(database, field, newValue, primaryKey, String.valueOf(pendingRentalId)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PendingRental generatePendingRental(ResultSet rs){
        try {
            return new PendingRental(rs.getInt("pending_rental_id"), rs.getString("cpr_nr"), rs.getString("car_model"),
                    rs.getString("color"), rs.getBoolean("afleveringsforsikring"), rs.getBoolean("selvrisiko"),
                    rs.getString("location"), rs.getDouble("monthly_fee"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
