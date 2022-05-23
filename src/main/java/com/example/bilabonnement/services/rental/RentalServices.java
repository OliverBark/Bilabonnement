package com.example.bilabonnement.services.rental;

import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.pending_rental.PendingRentalManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.sql.Date;
import java.time.LocalDate;

public class RentalServices {

    public void activatePendingRental(PendingRental pendingRental, double pricePrKm, LocalDate startDate, LocalDate endDate){
        RentalManager rentalManager = new RentalManager();
        System.out.println(pendingRental);
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        Rental rental = new Rental(0, pendingRental.getCustomerCPR(), pendingRental.getModel(),
                pendingRental.getColor(), pendingRental.isAfleveringsforsikring(), pendingRental.isSelvrisiko(),
                pendingRental.getLocation(), pricePrKm, Date.valueOf(startDate), Date.valueOf(endDate), pendingRental.getMonthlyFee(), true);
        rentalManager.createRental(rental);
        pendingRentalManager.deletePendingRental(pendingRental.getPendingRentalId());
    }
    public LocalDate generateDate(String date){
        String[] data = date.split("-");
        int[] dataINT = new int[3];
        for (int i = 0; i < dataINT.length; i++) {
            dataINT[i] = Integer.parseInt(data[i]);
        }
        return LocalDate.of(dataINT[0], dataINT[1], dataINT[2]);
    }
}