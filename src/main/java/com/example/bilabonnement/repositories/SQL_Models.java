package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.models.economy.SaleRecord;

public class SQL_Models {
    public String generateValues(Object object){
        if(object instanceof Customer){
            return generateCustomerValues((Customer) object);
        }
        if(object instanceof Damage){
            return generateDamageValues((Damage) object);
        }
        if(object instanceof DamageReport){
            return generateDamageReportValues((DamageReport) object);
        }
        if(object instanceof Payment){
            return generatePaymentValues((Payment) object);
        }
        if(object instanceof PendingRental){
            return generatePendingRentalValues((PendingRental) object);
        }
        if(object instanceof SaleRecord){
            return generateSaleRecordValues((SaleRecord) object);
        }
        if(object instanceof Rental){
            return generateRentalValues((Rental) object);
        }
        else {
            return "oof";
        }
    }
    private String generateCustomerValues(Customer customer){
        return "('" + customer.getFirstName() + "', '" +
                customer.getLastName() + "', '" +
                customer.getAddress() + "', '" +
                customer.getMobile() + "', '" +
                customer.getCprNr() + "', '" +
                customer.getRegNr() + "', '" +
                customer.getAccountNr() + "')";
    }
    private String generateDamageValues(Damage damage){
        return "('" + damage.getReportId() + "', '" +
                damage.getDamage() + "', '" +
                damage.getAmount() + "')";
    }
    private String generateDamageReportValues(DamageReport damageReport){
        return "('" + damageReport.getRentalId() + "', '" +
                damageReport.getDescription() + "')";
    }
    private String generatePaymentValues(Payment payment){
        return "('" + payment.getAmount() + "', '" +
                payment.getDate() + "', '" +
                payment.getTypeValue() + "', '" +
                payment.getRentalId() + "')";
    }
    private String generatePendingRentalValues(PendingRental pendingRental){
        return "('" + pendingRental.getCustomerCPR() + "', '" +
                pendingRental.getCarModel() + "', '" +
                pendingRental.getColor() + "', '" +
                booleanConversion(pendingRental.isAfleveringsforsikring()) + "', '" +
                booleanConversion(pendingRental.isSelvrisiko()) + "', '" +
                pendingRental.getLocation() + "', '" +
                pendingRental.getMonthlyFee() + "')";
    }
    private String generateSaleRecordValues(SaleRecord saleRecord){
        return "('" + saleRecord.getAmount() + "', '" +
                saleRecord.getType() + "', '" +
                saleRecord.getDate() + "', '" +
                saleRecord.getRentalId() + "')";
    }
    private String generateRentalValues(Rental rental){
        return "('" + rental.getCustomerCPR() + "', '" +
                rental.getCarModel() + "', '" +
                rental.getColor() + "', '" +
                booleanConversion(rental.isAfleveringsforsikring()) + "', '" +
                booleanConversion(rental.isSelvrisiko()) + "', '" +
                rental.getLocation() + "', '" +
                rental.getPricePrKm() + "', '" +
                rental.getStartDate() + "', '" +
                rental.getEndDate() + "', '" +
                rental.getMonthlyFee() + "', '" +
                booleanConversion(rental.isActive()) + "')";
    }
    private int booleanConversion(boolean input){
        if(input){
            return 1;
        } else {
            return 0;
        }
    }
}
