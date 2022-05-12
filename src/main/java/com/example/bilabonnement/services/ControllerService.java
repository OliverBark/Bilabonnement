package com.example.bilabonnement.services;

import javax.servlet.http.HttpSession;

public class ControllerService {

    public String errorPage(){
        return "Error/Error";
    }

    public HttpSession resetSession(HttpSession session){
        session.removeAttribute("username");
        return session;
    }
}
