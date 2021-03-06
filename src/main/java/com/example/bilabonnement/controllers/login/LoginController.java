package com.example.bilabonnement.controllers.login;

import com.example.bilabonnement.repositories.SQL_Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String index(){ return "login/login";}

    @GetMapping("/")
    public String login(HttpSession session){
        if(session.getAttribute("username")!=null){
            //Allerede logget ind
            return "redirect:/forside";
        }
        //Ikke logge ind
        return "login/login";
    }
    @PostMapping("/logging") //log into an existing user
    public String logging(WebRequest dataFromForm, HttpSession session) {
        SQL_Manager sql = new SQL_Manager();
        sql.start();
        try {
            if (sql.login(dataFromForm.getParameter("username"), dataFromForm.getParameter("password"))) {
                //successful login
                session.setAttribute("username", dataFromForm.getParameter("username"));
                return "redirect:/forside";
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
