package com.example.bilabonnement.controllers.Data.rentals;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.rental.RentalManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class DataRentalsController {

    @GetMapping("/data-view-active-rentals")
    public String viewActiveRentals(Model model){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getActiveRentals();
        model.addAttribute("activerentals", rentals);
        return "Data/view-active-rentals";
    }
    @GetMapping("/data-view-all-rentals")
    public String viewAllRentals(Model model){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        model.addAttribute("rentals", rentals);
        return "Data/view-all-rentals";
    }

    @PostMapping("/data-end-active-rental")
    public String endActiveRental(WebRequest dataFromForm){
        RentalManager rentalManager = new RentalManager();
        rentalManager.updateRental(Integer.parseInt(dataFromForm.getParameter("rental_id")), "active", "0");
        return "redirect:/data-view-active-rentals";
    }


}
