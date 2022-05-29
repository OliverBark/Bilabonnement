package com.example.bilabonnement.controllers;

import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.repositories.customer.CustomerManager;
import com.example.bilabonnement.repositories.pending_rental.PendingRentalManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BilabonnementTest {
    //Skal forestille sig at deres side tager brug af vores side gennem nu, /bilabonnement-test?data=.

    @GetMapping("/bilabonnement-test")
    public String testNewRental(){
        String data = "";
        //customer
        data += "SelfTestMan;SelfTested;TestingLand;20202020;010101-1517;2222;222222";
        //Forbindelse
        data += ";;";
        //new rental
        data += "010101-1517;selfmodel;colorself;true;false;testlandidk;3500";
        return "redirect:/simulate-new-rental?data=" + data;
    }

    @GetMapping("/bilabonnement-test-new-pending-rental")
    private String testNewRental2(@RequestParam String data){
        String[] dataSplit = data.split(";;");
        String customer = dataSplit[0];
        String rental = dataSplit[1];
        generateCustomer(customer);
        generateRental(rental);
        return "redirect:/data-menu-view-pending-rentals";

    }



    private void generateRental(String rentalData){
        String[] data = rentalData.split(";");
        String customerCPR = data[0];
        String model = data[1];
        String color = data[2];
        boolean afleveringsforsikring = Boolean.getBoolean(data[3]);
        boolean selvrisiko = Boolean.getBoolean(data[4]);
        String location = data[5];
        double monthlyFee = Double.parseDouble(data[6]);
        PendingRental pendingRental = new PendingRental(0, customerCPR, model, color, afleveringsforsikring, selvrisiko,
                location, monthlyFee);
        System.out.println("test?");
        System.out.println(pendingRental);
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        try {
            pendingRentalManager.deletePendingRental(pendingRental.getPendingRentalId());
            pendingRentalManager.createPendingRental(pendingRental);
        } catch (Exception e) {
            pendingRentalManager.createPendingRental(pendingRental);
        }
    }
    private void generateCustomer(String customerData){
        String[] data = customerData.split(";");
        String firstName = data[0];
        String lastName = data[1];
        String address = data[2];
        String mobile = data[3];
        String cprNr = data[4];
        int regNr = Integer.parseInt(data[5]);
        int accountNr = Integer.parseInt(data[6]);
        Customer customer = new Customer(firstName, lastName, address, mobile, cprNr,
                regNr, accountNr);
        System.out.println("test?");
        System.out.println(customer);
        CustomerManager customerManager = new CustomerManager();
        try {
            customerManager.deleteCustomer(customer.getCprNr());
            System.out.println("cusomter deleted");
            customerManager.createCustomer(customer);
        } catch (Exception e) {
            customerManager.createCustomer(customer);
        }
    }
/*
this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobile = mobile;
        this.cprNr = cprNr;
        this.regNr = regNr;
        this.accountNr = accountNr;

    private final int rentalId;
    private final String customerCPR;
    private final String model;
    private final String color;
    private final boolean afleveringsforsikring;
    private final boolean selvrisiko;
    private final String location;
    private final double pricePrKm;
    private final Date startDate;
    private final Date endDate;
    private final double monthlyFee;
    private final boolean active;

 */
}
