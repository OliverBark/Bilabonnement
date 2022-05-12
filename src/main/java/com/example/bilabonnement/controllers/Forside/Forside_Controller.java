package com.example.bilabonnement.controllers.Forside;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Forside_Controller {

    @GetMapping("/forside")
    public String index(){
        return "forside/index";
    }

}
