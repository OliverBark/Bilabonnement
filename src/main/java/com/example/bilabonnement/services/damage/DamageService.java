package com.example.bilabonnement.services.damage;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.damage.DamageManager;
import com.example.bilabonnement.repositories.damage_report.DamageReportManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import java.util.ArrayList;

public class DamageService {

    public double calculateDamagePrice(ArrayList<Damage> matrix, ArrayList<Damage> registered){
        ArrayList<Damage> result = new ArrayList<>();
        for (Damage damage : matrix) {
            for (Damage value : registered) {
                if (damage.getDamage().equalsIgnoreCase(value.getDamage())) {
                    result.add(damage);
                }
            }
        }
        double amount = 0;
        for (Damage damage : result) {
            amount += damage.getAmount();
        }
        return amount;
    }
}
