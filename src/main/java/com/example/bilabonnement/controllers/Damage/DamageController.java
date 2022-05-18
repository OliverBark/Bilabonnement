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
        session.setAttribute("rental", rentalManager.getSubscription(1));
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

    @GetMapping("/choose-rental")
    public String chooseRental(HttpSession session, Model model) {
        RentalManager rentalManager = new RentalManager();
        ArrayList<Rental> rentals = rentalManager.getSubscriptionList();
        model.addAttribute("rentals", rentals);
        return "Damage/rental-list";
    }

    @PostMapping("/choosing-rental")
    public String choosingRental(HttpSession session, WebRequest dataFromForm) {
        RentalManager rentalManager = new RentalManager();
        Rental rental = rentalManager.getSubscription(Integer.parseInt(dataFromForm.getParameter("rental_id")));
        session.setAttribute("rental", rental);
        return "redirect:/damage";
    }

    @GetMapping("/damage-report-page")
    public String damageReportPage(HttpSession session, Model model) {
        if (session.getAttribute("damage-report") == null) {
            return "redirect:/damage";
        }
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.findDamages(((DamageReport) session.getAttribute("damage-report")).getReportId());
        model.addAttribute("damages", damages);
        return "Damage/damage-report-page";
    }

    @GetMapping("/damage-report-create")
    public String damageReportCreate(HttpSession session){
        return "Damage/damage-report-create";
    }



    //Post Mappings
    @PostMapping("/create-damage-report")
    public String creatingDamageReport(HttpSession session, WebRequest dataFromForm){
        if(session.getAttribute("rental")==null){
            return "redirect:/damage";
        }
        DamageReportManager damageReportManager = new DamageReportManager();
        DamageReport report = new DamageReport(0, ((Rental) session.getAttribute("rental")).getRentalId(),
                dataFromForm.getParameter("report_description"));
        damageReportManager.createDamageRapport(report);
        return "redirect:/damage-page";
    }
    @PostMapping("/choosing-damage-report")
    public String choosingDamageReport(HttpSession session, WebRequest dataFromForm) {
        System.out.println("post start");
        DamageReportManager damageReportManager = new DamageReportManager();
        DamageReport temp = damageReportManager.getDamageRapport(Integer.parseInt(dataFromForm.getParameter("report_id")));
        System.out.println("damage report : " + temp);
        session.setAttribute("damage-report", temp);
        System.out.println("redirect");
        return "redirect:/damage-report-page";
    }

    @PostMapping("/add-damage")
    public String addDamage(HttpSession session, WebRequest dataFromForm) {
        DamageManager damageManager = new DamageManager();
        Damage temp = new Damage(0, ((DamageReport) session.getAttribute("damage-report")).getReportId(), dataFromForm.getParameter("damage_name"),
                Double.parseDouble(dataFromForm.getParameter("damage_price")));
        damageManager.createDamage(temp);
        return "redirect:/damage-report-page";
    }

    @PostMapping("/remove-damage")
    public String removeDamage(HttpSession session, WebRequest dataFromForm) {
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.findDamages(((DamageReport) session.getAttribute("damage-report")).getReportId());
        for (int i = 0; i < damages.size(); i++) {
            if(damages.get(i).getDamage().equalsIgnoreCase(dataFromForm.getParameter("damage_name_remove"))){
                damageManager.deleteDamage(damages.get(i).getDamageId());
                System.out.println("deleting: " + damages.get(i));
            }
        }
        return "redirect:/damage-report-page";
    }
}