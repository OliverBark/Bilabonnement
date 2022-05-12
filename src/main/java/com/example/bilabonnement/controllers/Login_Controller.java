package com.example.bilabonnement.controllers;


import com.example.bilabonnement.repositories.SQL_Manager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Login_Controller {

    @GetMapping("/login")
    public String index(){
        return "login/login";
    }

    @PostMapping("/logging") //log into an existing user
    public String logging(WebRequest dataFromForm, HttpSession session) {
        SQL_Manager sql = new SQL_Manager();
        try {
            if (sql.login(dataFromForm.getParameter("username"), dataFromForm.getParameter("password"))) {
                //successful login
                session.setAttribute("username", dataFromForm.getParameter("username"));
                return "redirect:/account";
            } else {
                //invalid login
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
