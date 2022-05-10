package com.example.bilabonnement.repositories.damages;


import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageRapport;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.repositories.SQL_String;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Damage_Manager {
    SQL_Manager sqlManager;
    SQL_String sqlString;
    private Damage_Data data;

    public DamageRapport getDamageRapport(String id, String value){
        try {
            Statement stmt = sqlManager.establishConnection();
            return generateDamageRapport(id, stmt.executeQuery("SELECT * FROM damage_rapport_" + id + " ORDER BY " + data.getPrimary_key()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DamageRapport generateDamageRapport(String id, ResultSet rs){
        ArrayList<Damage> damages = new ArrayList<>();
        try {
            while(rs.next()){
                damages.add(new Damage(rs.getString("damage"), rs.getDouble("price")));
            }
            return new DamageRapport(id, damages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
