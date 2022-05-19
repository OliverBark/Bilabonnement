package com.example.bilabonnement.services.statistic;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class StatisticService {

    //Calculates the monthly income of all rentals
    public double calculateTotalCarPrice(){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        double result = 0;
        for(Rental rental : rentals){
            result += rental.getMonthlyFee();
        }
        return result;
    }

    //Calculates the monthly income of active rentals
    public double calculateTotalCarPriceActive(){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        double result = 0;
        for(Rental rental : rentals){
            if(rental.isActive()) {
                result += rental.getMonthlyFee();
            }
        }
        return result;
    }

    //Gives you back the amount of active rentals
    public int calculateActiveRentals(){
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

    //Insert Start Date, End Date: Gives you the total monthly income during that interval
    public double calculatePriceDuring(Date startDate, Date endDate){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        double total = 0;
        for(Rental rental : rentals) {
            if(startDate.after(rental.getEndDate())|| endDate.before(rental.getStartDate())){
                System.out.println("outside time");
            }
            else if(startDate.equals(rental.getStartDate()) || startDate.after(rental.getStartDate())){
                if(endDate.equals(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(startDate, endDate);
                }
                if(endDate.before(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(startDate, endDate);
                }
                if(endDate.after(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(startDate, rental.getEndDate());
                }
            }
            else if(startDate.before(rental.getStartDate())){
                if(endDate.equals(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(rental.getStartDate(), endDate);
                }
                if(endDate.before(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(rental.getStartDate(), endDate);
                }
                if(endDate.after(rental.getEndDate())){
                    total += rental.getMonthlyFee() * getMonthsDifference(rental.getStartDate(), rental.getEndDate());
                }
            }
            System.out.println(total);
        }
        return total;
    }

    //gives the differences of two dates in months
    private int getMonthsDifference(Date date1, Date date2){
        int month1 = date1.getYear() * 12 + date1.getMonth();
        int month2 = date2.getYear() * 12 + date2.getMonth();
        return month2-month1+1;
    }
}
