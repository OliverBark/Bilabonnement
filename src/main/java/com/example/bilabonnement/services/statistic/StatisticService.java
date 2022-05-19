package com.example.bilabonnement.services.statistic;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.util.ArrayList;

public class StatisticService {

    public double calculateTotalCarPrice(){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        double result = 0;
        for(Rental rental : rentals){
            result += rental.getMonthlyFee();
        }
        return result;
    }

    public int calculateTotalCarsRented(){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        int result = 0;
        for(Rental rental : rentals){
            if(rental.isActive()){
                result++;
            }
        }
        return result;
    }
}
