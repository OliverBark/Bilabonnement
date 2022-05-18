package com.example.bilabonnement.controllers.Damage;

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
    public String damage(HttpSession session){
        if(session.getAttribute("rental")==null){
            return "redirect:/choose-rental";
        }
        session.removeAttribute("damage-report");
        return "redirect:/damage-page";
    }

    @GetMapping("/damage-test")
    public String damageTest(HttpSession session){
        RentalManager rentalManager = new RentalManager();
        session.setAttribute("rental", rentalManager.getSubscription(1));
        return "redirect:/damage-page";
    }

    @GetMapping("/damage-page")
    public String damagePage(HttpSession session, Model model){
        DamageReportManager damageReportManager = new DamageReportManager();
        ArrayList<DamageReport> reports =
                damageReportManager.findRentalDamageReports(((Rental) session.getAttribute("rental")).getRentalID());
        model.addAttribute("reports", reports);
        return "Damage/damage-page";
    }

    @GetMapping("/damage-report-page")
    public String damageReportPage(HttpSession session, Model model){
        if(session.getAttribute("damage-report")==null){
            return "redirect:/damage";
        }
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.findDamages(((DamageReport) session.getAttribute("damage-report")).getReport_id());
        model.addAttribute("damages", damages);
        return "damage-report-page";
    }

    @GetMapping("/choose-rental")
    public String chooseRental(HttpSession session, Model model){
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getSubscriptionList();
        model.addAttribute("rentals", rentals);
        return "Damage/rental-list";
    }

    @PostMapping("/choosing-rental")
    public String choosingRental(HttpSession session, WebRequest dataFromForm){
        RentalManager rentalManager = new RentalManager();
        Rental rental = rentalManager.getSubscription(Integer.parseInt(dataFromForm.getParameter("rental_id")));
        session.setAttribute("rental", rental);
        return "redirect:/damage";
    }

    @PostMapping("/choosing-damage-report")
    public String choosingDamageReport(HttpSession session, WebRequest dataFromForm){
        DamageReportManager damageReportManager = new DamageReportManager();
        DamageReport temp = damageReportManager.getDamageRapport(Integer.parseInt(dataFromForm.getParameter("report_id")));
        session.setAttribute("damage-report", temp);
        return "redirect:/damage-report-page";
    }


    private void resetDamage(HttpSession session){

    }


}
