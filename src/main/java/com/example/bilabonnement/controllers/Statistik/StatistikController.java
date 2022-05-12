package com.example.bilabonnement.controllers.Statistik;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatistikController {

    @GetMapping("/statistik")
    public String index() { return "statistik/index";}
}
