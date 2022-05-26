package com.example.bilabonnement.controllers.Data.frontpage;

import com.example.bilabonnement.services.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class DataController {

    @GetMapping("/data-menu")
    public String dataMenu(HttpSession session){
        return "Data/data-menu";
    }


}
