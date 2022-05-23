package com.example.bilabonnement.controllers.Data;

import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.repositories.pending_rental.PendingRentalManager;
import com.example.bilabonnement.services.rental.RentalServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DataController {
    @GetMapping("/data-menu")
    public String dataMenu(HttpSession session){
        return "Data/data-menu";
    }

    @GetMapping("/data-menu-view-pending-rentals")
    public String viewPendingRentals(HttpSession session, Model model){
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        ArrayList<PendingRental> pendingRentals = new ArrayList<>();
        pendingRentals = pendingRentalManager.getPendingRentalList();
        model.addAttribute("rentals", pendingRentals);
        return "Data/view-pending-rentals";
    }
    @GetMapping("/data-menu-edit-pending-rental")
    public String editPendingRentals(HttpSession session, Model model){
        //edit some informations if needed
        //an activate button
        return "Data/edit-pending-rental";
    }
    @PostMapping("activate-pending-rental")
    public String activatePendingRental(HttpSession session, WebRequest dataFromForm){
        RentalServices rentalServices = new RentalServices();
        PendingRental pendingRental = (PendingRental) session.getAttribute("pending-rental");
        System.out.println("start: " + dataFromForm.getParameter("start_date"));
        System.out.println("end: " + dataFromForm.getParameter("end_date"));
        rentalServices.activatePendingRental(pendingRental,
                Double.parseDouble(dataFromForm.getParameter("price_pr_km")),
                rentalServices.generateDate(dataFromForm.getParameter("start_date")),
                rentalServices.generateDate(dataFromForm.getParameter("end_date")));
        return "";
    }
    @PostMapping("/choose-pending-rental")
    public String choosePendingRental(HttpSession session, WebRequest dataFromForm){
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        PendingRental temp = pendingRentalManager.getPendingRental(Integer.parseInt(dataFromForm.getParameter("pending_rental_id")));
        session.setAttribute("pending-rental", temp);
        System.out.println(temp);
        return "redirect:/data-menu-edit-pending-rental";
    }
}