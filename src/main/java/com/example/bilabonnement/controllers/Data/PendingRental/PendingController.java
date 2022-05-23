package com.example.bilabonnement.controllers.Data.PendingRental;

import com.example.bilabonnement.models.data.PendingRental;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PendingController {

    @RequestMapping(value = "pendingrental", method = RequestMethod.GET)
    public String index(Model model){
        PendingRental prtest = new PendingRental(9, "0000000000",
                "Volvo", "Blå", true, false,
                "København", 100.00);
        model.addAttribute("prtest", prtest);
        return "dataregistrering/pendingrental";
    }
}
