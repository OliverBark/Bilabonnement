package com.example.bilabonnement.controllers.damagerapport;

import com.example.bilabonnement.models.damage.Damage;
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
public class DamageRapport {

    @GetMapping("/new-damage-rapport")
    public String newDamageRapport(HttpSession session){
        //Test Function right now

        //Before going to create-damage-rapport
        //session(damage-rapport-name) could be name of owner and type of rapport
        //session(damage-rapport-id) needs to be made
        DamageManager damageManager = new DamageManager();
        DamageReportManager damageReportManager = new DamageReportManager();
        session.setAttribute("damage-rapport-name", "Test Damage Rapport");
        session.setAttribute("damage-rapport-id", 1);
        return "redirect:/create-damage-rapport";
    }

    @GetMapping("/create-damage-rapport")
    public String createDamageRapport(HttpSession session, Model model){
        DamageManager damageManager = new DamageManager();
        model.addAttribute("damage_name", session.getAttribute("damage-rapport-name"));
        model.addAttribute("damages", damageManager.findDamages(((Integer) session.getAttribute("damage-rapport-id"))));
        return "skadesregistrering/create-damage-rapport";
    }
    @GetMapping("/submit-damage-rapport")
    public String submitDamageRapport(HttpSession session){
        /*String damageRapportID = (String) session.getAttribute("damage-rapport-name");
        ArrayList<Damage> damages = (ArrayList<Damage>) session.getAttribute("damage-rapport-damages");
        damageRapportManger.createDamageRapport(damageRapportID, damages);*/
        return "redirect:/";
    }

    @PostMapping("/remove-damage1")
    public String removeDamage(HttpSession session, WebRequest dataFromForm){
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.getDamageList();
        for (int i = 0; i < damages.size(); i++) {
            System.out.println(damages.get(i));
            if(damages.get(i).getReportId() == (Integer) session.getAttribute("damage-rapport-id")){
                if(damages.get(i).getDamage().equalsIgnoreCase(dataFromForm.getParameter("damage_name_remove"))){
                    damageManager.deleteDamage(damages.get(i).getDamageId());
                }
            }
        }
        return "redirect:/create-damage-rapport";
    }

    @PostMapping("/add-damage1")
    public String addDamage(HttpSession session, WebRequest dataFromForm){
        DamageManager damageManager = new DamageManager();
        Damage tempDamage = new Damage(0, ((Integer) session.getAttribute("damage-rapport-id")),
        dataFromForm.getParameter("damage_name"), Double.parseDouble(dataFromForm.getParameter("damage_price")));
        damageManager.createDamage(tempDamage);
        return "redirect:/create-damage-rapport";
    }


}
