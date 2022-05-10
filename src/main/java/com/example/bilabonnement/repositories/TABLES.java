package com.example.bilabonnement.repositories;

import com.example.bilabonnement.models.sql.TableSection;

import java.util.ArrayList;

public class TABLES {
    public void createTable(String tableName, ArrayList<TableSection> sections){
        String create = "CREATE TABLE " + tableName + "(";
        for (int i = 0; i < sections.size(); i++) {
            create += sections.get(i).getName() + " " + sections.get(i).getAttribute();
            if(i < sections.size()){
                create += ", ";
            }
        }
    }
    public void deleteTable(String tableName){
        String delete = "DROP TABLE " + tableName;
    }
}
