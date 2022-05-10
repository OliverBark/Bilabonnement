package com.example.bilabonnement.controllers.login;

import com.example.bilabonnement.models.admin.User;
import com.example.bilabonnement.repositories.SQL_Manager;
import com.example.bilabonnement.services.ControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

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

    @GetMapping("logout")
    public String logout(HttpSession session, @RequestParam String status){
        if(status!=null){
            switch (status){
                case "true":
                    session.removeAttribute("username");

            }
        }
        return "login/logout";
    }

    @PostMapping("logging-in")
    public String loggingIn(HttpSession session, WebRequest dataFromForm){
        SQL_Manager sqlManager = new SQL_Manager();
        try {
            User user = sqlManager.getUser(dataFromForm.getParameter("username"), dataFromForm.getParameter("password"));
            if(user == null){
                return "redirect:/login?status=invalid";
            }
            session.setAttribute("username", user.getUsername());
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
