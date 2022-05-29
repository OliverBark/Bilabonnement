package com.example.bilabonnement.controllers.Damage.frontpage;

import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.damage_report.DamageReportManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DamageController {

    @GetMapping("/damage")
    public String damage(HttpSession session) {
        if (session.getAttribute("rental") == null) {
            return "redirect:/choose-rental";
        }
        session.removeAttribute("damage-report");
        return "redirect:/damage-page";
    }

    @GetMapping("/damage-test")
    public String damageTest(HttpSession session) {
        RentalManager rentalManager = new RentalManager();
        session.setAttribute("rental", rentalManager.getRental(1));
        return "redirect:/damage";
    }

    @GetMapping("/damage-page")
    public String damagePage(HttpSession session, Model model) {
        DamageReportManager damageReportManager = new DamageReportManager();
        ArrayList<DamageReport> reports =
                damageReportManager.findRentalDamageReports(((Rental) session.getAttribute("rental")).getRentalId());
        model.addAttribute("reports", reports);
        return "Damage/damage-page";
    }
}