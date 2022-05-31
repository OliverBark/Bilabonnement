package com.example.bilabonnement.repositories.car;

import com.example.bilabonnement.models.data.Car;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_Models;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CarManager {
    SQL_Manager sqlManager = new SQL_Manager();
    SQL_String sqlString = new SQL_String();
    SQL_Models sqlModels = new SQL_Models();
    private final String database = "Cars";
    private final String primaryKey = "vehicle_number";
    private final String sections = "(vehicle_number, stel_number, brand, model, equipment_level, steel_price, co2_exhaust, rental_id)";

    public Car getCar(String cprNr){
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getData(database, primaryKey, cprNr));
            rs.next();
            return generateCar(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Car> getCarList(){
        ArrayList<Car> cars = new ArrayList<>();
        try {
            Statement stmt = sqlManager.establishConnection();
            ResultSet rs = stmt.executeQuery(sqlString.getDataList(database, primaryKey));
            while(rs.next()){
                cars.add(generateCar(rs));
            }
            return cars;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void createCar(Car car){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.createData(database, sections, sqlModels.generateValues(car)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCar(String vehicleNumber){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.deleteData(database, primaryKey, String.valueOf(vehicleNumber)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateCar(String vehicleNumber, String field, String newValue){
        try {
            Statement stmt = sqlManager.establishConnection();
            stmt.executeUpdate(sqlString.updateData(database, field, newValue, primaryKey, String.valueOf(vehicleNumber)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Car generateCar(ResultSet rs){
        try {
            return new Car(rs.getString("vehicle_nr"), rs.getString("stel_number"), rs.getString("brand"),
                    rs.getString("model"), rs.getString("equipment_level"), rs.getDouble("steel_price"),
                    rs.getDouble("co_exhaust"), rs.getInt("rental_id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
