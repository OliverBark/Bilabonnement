package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.data.PendingSubscription;
import com.example.bilabonnement.models.data.Subscription;
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
        if(object instanceof DamageRapport){
            return generateDamageRapportValues((DamageRapport) object);
        }
        if(object instanceof Payment){
            return generatePaymentValues((Payment) object);
        }
        if(object instanceof PendingSubscription){
            return generatePendingSubscriptionValues((PendingSubscription) object);
        }
        if(object instanceof SalesRecord){
            return generateSalesRecordValues((SalesRecord) object);
        }
        if(object instanceof Subscription){
            return generateSubscriptionValues((Subscription) object);
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
        return "('" + damage.getRapportID() + "', '" +
                damage.getDamage() + "', '" +
                damage.getAmount() + "')";
    }
    public String generateDamageRapportValues(DamageRapport damageRapport){
        return "('" + damageRapport.getId() + "', '" +
                damageRapport.getSubscription_id() + "', '" +
                damageRapport.getDescription() + "')";
    }
    public String generatePaymentValues(Payment payment){
        return "('" + payment.getPaymentID() + "', '" +
                payment.getAmount() + "', '" +
                payment.getDate() + "', '" +
                payment.getSubscriptionID() + "')";
    }
    public String generatePendingSubscriptionValues(PendingSubscription pendingSubscription){
        return "('" + pendingSubscription.getId() + "', '" +
                pendingSubscription.getCustomerCPR() + "', '" +
                pendingSubscription.getModel() + "', '" +
                pendingSubscription.getColor() + "', '" +
                pendingSubscription.isAfleveringsforsikring() + "', '" +
                pendingSubscription.isSelvrisiko() + "', '" +
                pendingSubscription.getLocation() + "', '" +
                pendingSubscription.getMonthlyFee() + "')";
    }
    public String generateSalesRecordValues(SalesRecord salesRecord){
        return "('" + salesRecord.getPaymentId() + "', '" +
                salesRecord.getAmount() + "', '" +
                salesRecord.getType() + "', '" +
                salesRecord.getDate() + "')";
    }
    public String generateSubscriptionValues(Subscription subscription){
        return "('" + subscription.getSubscriptionID() + "', '" +
                subscription.getCustomerCPR() + "', '" +
                subscription.getModel() + "', '" +
                subscription.getColor() + "', '" +
                subscription.isAfleveringsforsikring() + "', '" +
                subscription.isSelvrisiko() + "', '" +
                subscription.getLocation() + "', '" +
                subscription.getPricePrKm() + "', '" +
                subscription.getStartDate() + "', '" +
                subscription.getEndDate() + "', '" +
                subscription.getMonthlyFee() + "', '" +
                subscription.isActive() + "')";
    }


}
