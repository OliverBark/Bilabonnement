package com.example.bilabonnement.controllers.Damage.frontpage;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.damage.DamageManager;
import com.example.bilabonnement.repositories.damage_report.DamageReportManager;
import com.example.bilabonnement.repositories.rental.RentalManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DamageController {
    /*
    session - 'rental' : The current subscription worked on
    session - 'damage-rapport' : The current damage-rapport worked on
     */

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
        System.out.println("page found");
        return "Damage/damage-page";
    }
}