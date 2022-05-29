package com.example.bilabonnement.controllers.Data.frontpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DataController {

    @GetMapping("/data-menu")
    public String dataMenu(){
        return "Data/data-menu";
    }


}
