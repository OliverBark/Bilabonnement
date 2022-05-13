package com.example.bilabonnement.models.data;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String mobile;
    private final String cprNr;
    private final String regNr;
    private final String accountNr;

    public Customer(String firstName, String lastName, String address, String mobile, String cprNr, String regNr, String accountNr){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.cprNr = cprNr;
        this.regNr = regNr;
        this.accountNr = accountNr;
    }

    //Get List
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getMobile() {
        return mobile;
    }

    public String getCprNr() {
        return cprNr;
    }

    public String getRegNr() {
        return regNr;
    }
    public String getAccountNr() {
        return accountNr;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", cprNr='" + cprNr + '\'' +
                '}';
    }
}
