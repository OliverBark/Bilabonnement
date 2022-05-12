package com.example.bilabonnement.models.economy;

import java.util.ArrayList;

public class Contingent {
    private final String contingentID;
    private final ArrayList<Payment> payments;


    public Contingent(String contingentID, ArrayList<Payment> payments) {
        this.contingentID = contingentID;
        this.payments = payments;
    }

    public String getContingentID() {
        return contingentID;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }
}
