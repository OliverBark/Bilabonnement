package com.example.bilabonnement.repositories.costumer;

import com.example.bilabonnement.models.data.Costumer;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class CostumerManagerTest {

    @Test
    void getCostumer() {
        //ARRANGE
        CostumerManager costumerManager = new CostumerManager();
        String cpr = "cpr-test";
        //ACT

        //ASSERT
    }

    @Test
    void getCostumerList() {
    }

    @Test
    void createCostumer() {
        //Arrange
        CostumerManager costumerManager = new CostumerManager();
        Costumer test = new Costumer("Jack", "Sparrow", "Caribbean", "20202020", "020202-0202", "2002", "2002002");
        //Act
        costumerManager.createCostumer(test);
        //Assert
        Assert.isTrue(test == costumerManager.getCostumer("020202-0202"));
    }

    @Test
    void deleteCostumer() {
    }
}