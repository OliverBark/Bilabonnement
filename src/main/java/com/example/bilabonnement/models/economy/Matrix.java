package com.example.bilabonnement.models.economy;

import com.example.bilabonnement.models.damage.Damage;

import java.util.ArrayList;

public class Matrix {
    private final String matrix_id;
    private final ArrayList<Damage> damages;

    public Matrix(String matrix_id, double pricePrKm, ArrayList<Damage> damages) {
        this.matrix_id = matrix_id;
        this.damages = damages;
    }

    public String getMatrix_id() {
        return matrix_id;
    }

    public ArrayList<Damage> getDamages() {
        return damages;
    }
}
