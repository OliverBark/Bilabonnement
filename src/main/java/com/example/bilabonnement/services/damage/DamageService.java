package com.example.bilabonnement.services.damage;

import com.example.bilabonnement.models.damage.Damage;

import java.util.ArrayList;

public class DamageService {

    //Indsæt en damage liste med skaderne samt priserne aftalt i matrix
    //Indsæt en damage liste med skaderne registreret efter aflevering i registered
    //Tager kun stilling til priserne i matrix, registered kan være -1, 0 eller 1000, det er ligegyldigt
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
