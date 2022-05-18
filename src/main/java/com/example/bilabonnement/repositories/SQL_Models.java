package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.models.economy.SalesRecord;

public class SQL_Models {
    public String generateValues(Object object){
        if(object instanceof Customer){
            return generateCustomerValues((Customer) object);
        }
        if(object instanceof Damage){
            return generateDamageValues((Damage) object);
        }
        if(object instanceof DamageReport){
            return generateDamageRapportValues((DamageReport) object);
        }
        if(object instanceof Payment){
            return generatePaymentValues((Payment) object);
        }
        if(object instanceof PendingRental){
            return generatePendingSubscriptionValues((PendingRental) object);
        }
        if(object instanceof SalesRecord){
            return generateSalesRecordValues((SalesRecord) object);
        }
        if(object instanceof Rental){
            return generateSubscriptionValues((Rental) object);
        }
        else {
            return "oof";
        }
    }
    public String generateCustomerValues(Customer customer){
        return "('" + customer.getFirstName() + "', '" +
                customer.getLastName() + "', '" +
                customer.getAddress() + "', '" +
                customer.getMobile() + "', '" +
                customer.getCprNr() + "', '" +
                customer.getRegNr() + "', '" +
                customer.getAccountNr() + "')";
    }
    public String generateDamageValues(Damage damage){
        return "('" + damage.getReportID() + "', '" +
                damage.getDamage() + "', '" +
                damage.getAmount() + "')";
    }
    public String generateDamageRapportValues(DamageReport damageReport){
        return "('" + damageReport.getId() + "', '" +
                damageReport.getRental_id() + "', '" +
                damageReport.getDescription() + "')";
    }
    public String generatePaymentValues(Payment payment){
        return "('" + payment.getPaymentID() + "', '" +
                payment.getAmount() + "', '" +
                payment.getDate() + "', '" +
                payment.getRentalID() + "')";
    }
    public String generatePendingSubscriptionValues(PendingRental pendingRental){
        return "('" + pendingRental.getPendingRentalId() + "', '" +
                pendingRental.getCustomerCPR() + "', '" +
                pendingRental.getModel() + "', '" +
                pendingRental.getColor() + "', '" +
                pendingRental.isAfleveringsforsikring() + "', '" +
                pendingRental.isSelvrisiko() + "', '" +
                pendingRental.getLocation() + "', '" +
                pendingRental.getMonthlyFee() + "')";
    }
    public String generateSalesRecordValues(SalesRecord salesRecord){
        return "('" + salesRecord.getPaymentId() + "', '" +
                salesRecord.getAmount() + "', '" +
                salesRecord.getType() + "', '" +
                salesRecord.getDate() + "')";
    }
    public String generateSubscriptionValues(Rental rental){
        return "('" + rental.getRentalID() + "', '" +
                rental.getCustomerCPR() + "', '" +
                rental.getModel() + "', '" +
                rental.getColor() + "', '" +
                rental.isAfleveringsforsikring() + "', '" +
                rental.isSelvrisiko() + "', '" +
                rental.getLocation() + "', '" +
                rental.getPricePrKm() + "', '" +
                rental.getStartDate() + "', '" +
                rental.getEndDate() + "', '" +
                rental.getMonthlyFee() + "', '" +
                rental.isActive() + "')";
    }


}
