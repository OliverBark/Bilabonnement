package com.example.bilabonnement.repositories;

public class SQL_String {

    public String getData(String database, String primary_key, String value){
        return "SELECT * FROM " + database + " WHERE " + primary_key + " = '" + value + "'";
    }
    public String getDataList(String database, String primary_key){
        return "SELECT * FROM " + database + " ORDER BY " + primary_key;
    }
    public String createData(String database, String sections, String values){
        return "INSERT INTO " + database + sections +
                "VALUES" + values;
    }
    public String deleteData(String database, String primary_key, String value){
        return "DELETE FROM " + database + " WHERE " + primary_key + " = '" + value + "'";
    }
    public String createTable(String database, String tableSections){
        return "CREATE TABLE " + database + tableSections;
    }
    public String deleteTable(String database){
        return "DROP TABLE " + database;
    }
}
