package com.example.bilabonnement.repositories.contingent_id;

import com.example.bilabonnement.models.data.Costumer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContingentManagerTest {

    @Test
    void getContingent() {
        //ARRANGE
        ContingentManager contingentManager = new ContingentManager();
        //ACT
        contingentManager.getContingent(new Costumer("first", "last", "address", "mobile", "cpr", "reg",
                "account"));

        //ASSERT
    }

    @Test
    void createContingent() {
    }

    @Test
    void deleteContingentData() {
    }

    @Test
    void deleteContingentList() {
    }
}