package com.example.bilabonnement.models.damage;

import java.util.ArrayList;

public class DamageRapport {
    /*
    damagerapport_matrix_id
    damagerapport_registered_id
     */
    private final String damageRapportID;
    private final ArrayList<Damage> damages;

    public DamageRapport(String damageRapportID, ArrayList<Damage> damages) {
        this.damages = damages;
        this.damageRapportID = damageRapportID;
    }

    public String getDamageRapportID() {
        return damageRapportID;
    }

    public ArrayList<Damage> getDamages() {
        return damages;
    }
}
