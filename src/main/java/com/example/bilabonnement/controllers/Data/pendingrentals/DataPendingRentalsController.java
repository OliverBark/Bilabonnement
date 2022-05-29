package com.example.bilabonnement.controllers.Data.pendingrentals;

import com.example.bilabonnement.models.data.PendingRental;
import com.example.bilabonnement.repositories.pending_rental.PendingRentalManager;
import com.example.bilabonnement.services.ControllerService;
import com.example.bilabonnement.services.rental.RentalServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DataPendingRentalsController {

    @GetMapping("/data-menu-view-pending-rentals")
    public String viewPendingRentals(Model model){
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        ArrayList<PendingRental> pendingRentals = pendingRentalManager.getPendingRentalList();
        model.addAttribute("rentals", pendingRentals);
        return "Data/view-pending-rentals";
    }

    @GetMapping("/data-menu-edit-pending-rental")
    public String editPendingRentals(HttpSession session, Model model){
        if(session.getAttribute("pending-rental") == null){
            return "redirect:/data-menu";
        }

        model.addAttribute("rentals", session.getAttribute("pending-rental"));
        return "Data/edit-pending-rental";
    }

    @PostMapping("activate-pending-rental")
    public String activatePendingRental(HttpSession session, WebRequest dataFromForm){
        ControllerService controllerService = new ControllerService();
        if(session.getAttribute("pending-rental") == null){
            return "redirect:/data-menu";
        }

        RentalServices rentalServices = new RentalServices();
        PendingRental pendingRental = (PendingRental) session.getAttribute("pending-rental");
        rentalServices.activatePendingRental(pendingRental,
                Double.parseDouble(dataFromForm.getParameter("price_pr_km")),
                controllerService.generateDate(dataFromForm.getParameter("start_date")),
                controllerService.generateDate(dataFromForm.getParameter("end_date")));
        session.removeAttribute("pending-rental");
        return "redirect:/data-menu";
    }

    @PostMapping("/choose-pending-rental")
    public String choosePendingRental(HttpSession session, WebRequest dataFromForm){
        PendingRentalManager pendingRentalManager = new PendingRentalManager();
        PendingRental temp = pendingRentalManager.getPendingRental(Integer.parseInt(dataFromForm.getParameter("pending_rental_id")));
        session.setAttribute("pending-rental", temp);
        return "redirect:/data-menu-edit-pending-rental";
    }
}
