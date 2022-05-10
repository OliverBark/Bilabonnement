package com.example.bilabonnement.models.sql;

public class TableSection {
    private final String name;
    private final String attribute;

    public TableSection(String name, String attribute) {
        this.name = name;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public String getAttribute() {
        return attribute;
    }
}
