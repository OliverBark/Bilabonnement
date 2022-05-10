package com.example.bilabonnement.controllers.login;

import com.example.bilabonnement.models.admin.User;
import com.example.bilabonnement.repositories.SQL_Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    @PostMapping("logging")
    public String logging(HttpSession session, WebRequest dataFromForm){
        SQL_Manager sqlManager = new SQL_Manager();
        try {
            User user = sqlManager.getUser(dataFromForm.getParameter("username"), dataFromForm.getParameter("password"));
            if(user == null){
                return "";
            }
            session.setAttribute("username", user.getUsername());
            return "";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
