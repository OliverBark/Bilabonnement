package com.example.bilabonnement.controllers.login;

import com.example.bilabonnement.services.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    ControllerService conService = new ControllerService();

    @GetMapping("login")
    public String login(HttpSession session, @RequestParam String status){
        if(session!=null){
            return "login/logout";
        }
        return "login/login";
    }



}
