package com.example.bilabonnement.controllers.damagerapport;

import com.example.bilabonnement.models.damage.Damage;
import com.example.bilabonnement.repositories.tabledata.damagerapport.DamageRapportManger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Objects;

@Controller
public class DamageRapport {
    @GetMapping("/new-damage-rapport")
    public String newDamageRapport(HttpSession session, @RequestParam String subscriptionID){
        //used for adding matrix for a subscription: generates damage and their prices in a list
        //only used for damage matrix, for registered rapports showing the damages either have
        //user manually input total price, or make a place where u can check them off and get price

        //when using make sure that /new-damage-rapport?subscriptionID='the id rapport is for'
        session.setAttribute("damage-rapport-name", "damagerapport_" + "matrix" + "_" + subscriptionID);
        session.setAttribute("damage-rapport-damages", new ArrayList<Damage>());
        return "redirect:/create-damage-rapport";
    }

    @GetMapping("/create-damage-rapport")
    public String createDamageRapport(HttpSession session, Model model){
        model.addAttribute("damages", session.getAttribute("damage-rapport-damages"));
        model.addAttribute("damage-name", session.getAttribute("damage-rapport-name"));
        return "damagerapports/create-damage-rapport";
    }
    @GetMapping("/submit-damage-rapport")
    public String submitDamageRapport(HttpSession session){
        DamageRapportManger damageRapportManger = new DamageRapportManger();
        String damageRapportID = (String) session.getAttribute("damage-rapport-name");
        ArrayList<Damage> damages = (ArrayList<Damage>) session.getAttribute("damage-rapport-damages");
        damageRapportManger.createDamageRapport(damageRapportID, damages);
        return "redirect:/";
    }

    @PostMapping("/remove-damage")
    public String removeDamage(HttpSession session, WebRequest dataFromForm){
        System.out.println("remove damage");
        ArrayList<Damage> damages = (ArrayList<Damage>) session.getAttribute("damage-rapport-damages");
        for (int i = 0; i < damages.size(); i++) {
            if(damages.get(i).getDamage().equals(dataFromForm.getParameter("damage_name_remove"))){
                damages.remove(i);
            }

        }
        System.out.println("damage removed");
        return "redirect:/create-damage-rapport";
    }

    @PostMapping("/add-damage")
    public String addDamage(HttpSession session, WebRequest dataFromForm){
        ArrayList<Damage> damages = (ArrayList<Damage>) session.getAttribute("damage-rapport-damages");
        Damage damage = new Damage(dataFromForm.getParameter("damage_name"), Double.parseDouble(dataFromForm.getParameter("damage_price")));
        damages.add(damage);
        return "redirect:/create-damage-rapport";
    }


}
