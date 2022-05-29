package com.example.bilabonnement.services.statistic;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.repositories.payment.PaymentManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthlyFeeService {

    public void newMonth(){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> activeRentals = rentalManager.getActiveRentals();
        newMonthPayments(activeRentals);
    }

    public void newMonthPayments(ArrayList<Rental> activeRentals){
        PaymentManager paymentManager = new PaymentManager();
        for (int i = 0; i < activeRentals.size(); i++) {
            Payment payment = new Payment(0, activeRentals.get(i).getMonthlyFee(),
                    Date.valueOf(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1)),
                    "MONTHLY",
                    activeRentals.get(i).getRentalId());
            System.out.println(payment);
            if(!paymentManager.alreadyExistsPayment(payment)) {
                paymentManager.createPayment(payment);
            }
        }
    }

    public static void main(String[] args) {
        MonthlyFeeService monthlyFeeService = new MonthlyFeeService();
        monthlyFeeService.newMonth();
    }
}
