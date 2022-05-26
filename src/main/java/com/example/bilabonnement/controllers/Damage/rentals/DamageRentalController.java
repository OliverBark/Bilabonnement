package com.example.bilabonnement.controllers.Damage.rentals;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.rental.RentalManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DamageRentalController {
    @GetMapping("/choose-rental")
    public String chooseRental(HttpSession session, Model model) {
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getRentalList();
        model.addAttribute("rentals", rentals);
        return "Damage/rental-list";
    }

    @PostMapping("/choosing-rental")
    public String choosingRental(HttpSession session, WebRequest dataFromForm) {
        RentalManager rentalManager = new RentalManager();
        Rental rental = rentalManager.getRental(Integer.parseInt(dataFromForm.getParameter("rental_id")));
        session.setAttribute("rental", rental);
        return "redirect:/damage";
    }
}
