package com.example.bilabonnement.models.data;

public class Car {
    private final String vehicleNumber;
    private final String stelNumber;
    private final String brand;
    private final String model;
    private final String equipmentLevel;
    private final double steelPrice;
    private final double co2Exhaust;
    private final int rentalId;

    public Car(String vehicleNumber, String stelNumber, String brand, String model, String equipmentLevel,
               double steelPrice, double co2Exhaust, int rentalId) {
        this.vehicleNumber = vehicleNumber;
        this.stelNumber = stelNumber;
        this.brand = brand;
        this.model = model;
        this.equipmentLevel = equipmentLevel;
        this.steelPrice = steelPrice;
        this.co2Exhaust = co2Exhaust;
        this.rentalId = rentalId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getStelNumber() {
        return stelNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public double getSteelPrice() {
        return steelPrice;
    }

    public double getCo2Exhaust() {
        return co2Exhaust;
    }

    public int getRentalId() {
        return rentalId;
    }
}
