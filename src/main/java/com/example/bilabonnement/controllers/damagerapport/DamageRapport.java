package com.example.bilabonnement.controllers.damagerapport;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.repositories.damage.DamageManager;
import com.example.bilabonnement.repositories.damage_rapport.DamageRapportManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class DamageRapport {

    @GetMapping("/new-damage-rapport")
    public String newDamageRapport(HttpSession session){
        DamageManager damageManager = new DamageManager();
        DamageRapportManager damageRapportManager = new DamageRapportManager();
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

    @PostMapping("/remove-damage")
    public String removeDamage(HttpSession session, WebRequest dataFromForm){
        System.out.println("remove damage");
        DamageManager damageManager = new DamageManager();
        ArrayList<Damage> damages = damageManager.getDamageList();
        for (int i = 0; i < damages.size(); i++) {
            System.out.println("i - " + i);
            System.out.println(damages.get(i));
            if(damages.get(i).getRapportID() == (Integer) session.getAttribute("damage-rapport-id")){
                System.out.println("first if");
                if(damages.get(i).getDamage().equalsIgnoreCase(dataFromForm.getParameter("damage_name_remove"))){
                    System.out.println("second if");
                    damageManager.deleteDamage(damages.get(i).getDamageID());
                }
            }
        }
        return "redirect:/create-damage-rapport";
    }

    @PostMapping("/add-damage")
    public String addDamage(HttpSession session, WebRequest dataFromForm){
        DamageManager damageManager = new DamageManager();
        Damage tempDamage = new Damage(0, ((Integer) session.getAttribute("damage-rapport-id")),
        dataFromForm.getParameter("damage_name"), Double.parseDouble(dataFromForm.getParameter("damage_price")));
        damageManager.createDamage(tempDamage);
        return "redirect:/create-damage-rapport";
    }


}
