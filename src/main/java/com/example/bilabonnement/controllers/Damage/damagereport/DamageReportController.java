package com.example.bilabonnement.controllers.Damage.damagereport;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.models.damage.DamageReport;
import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.damage.DamageManager;
import com.example.bilabonnement.repositories.damage_report.DamageReportManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DamageReportController {

    @GetMapping("/damage-report-page")
    public String damageReportPage(HttpSession session, Model model) {
        if (session.getAttribute("damage-report") == null) {
            return "redirect:/damage";
        }
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.findReportDamages(((DamageReport) session.getAttribute("damage-report")).getReportId());
        model.addAttribute("damages", damages);
        return "Damage/damage-report-page";
    }

    @GetMapping("/damage-report-create")
    public String damageReportCreate(){
        return "Damage/damage-report-create";
    }

    @PostMapping("/create-damage-report")
    public String creatingDamageReport(HttpSession session, WebRequest dataFromForm){
        if(session.getAttribute("rental")==null){
            return "redirect:/damage";
        }
        DamageReportManager damageReportManager = new DamageReportManager();
        DamageReport report = new DamageReport(0, ((Rental) session.getAttribute("rental")).getRentalId(),
                dataFromForm.getParameter("report_description"));
        damageReportManager.createDamageReport(report);
        return "redirect:/damage-page";
    }

    @PostMapping("/choosing-damage-report")
    public String choosingDamageReport(HttpSession session, WebRequest dataFromForm) {
        DamageReportManager damageReportManager = new DamageReportManager();
        DamageReport temp = damageReportManager.getDamageReport(Integer.parseInt(dataFromForm.getParameter("report_id")));
        session.setAttribute("damage-report", temp);
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
        ArrayList<Damage> damages = damageManager.findReportDamages(((DamageReport) session.getAttribute("damage-report")).getReportId());
        for (int i = 0; i < damages.size(); i++) {
            if(damages.get(i).getDamage().equalsIgnoreCase(dataFromForm.getParameter("damage_name_remove"))){
                damageManager.deleteDamage(damages.get(i).getDamageId());
            }
        }
        return "redirect:/damage-report-page";
    }
}
