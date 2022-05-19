package com.example.bilabonnement.services.rental;

import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.pending_rental.PendingRentalManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.sql.Date;

public class RentalServices {

    public void activatePendingRental(PendingRental pendingRental, double pricePrKm, Date startDate, Date endDate){
        RentalManager rentalManager = new RentalManager();
        System.out.println(pendingRental);
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        Rental rental = new Rental(0, pendingRental.getCustomerCPR(), pendingRental.getModel(),
                pendingRental.getColor(), pendingRental.isAfleveringsforsikring(), pendingRental.isSelvrisiko(),
                pendingRental.getLocation(), pricePrKm, startDate, endDate, pendingRental.getMonthlyFee(), true);
        rentalManager.createRental(rental);
        pendingRentalManager.deletePendingRental(pendingRental.getPendingRentalId());
    }
}
