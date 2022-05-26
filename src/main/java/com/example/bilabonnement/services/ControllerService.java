package com.example.bilabonnement.services;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class ControllerService {

    public LocalDate generateDate(String date){
        String[] data = date.split("-");
        int[] dataINT = new int[3];
        for (int i = 0; i < dataINT.length; i++) {
            dataINT[i] = Integer.parseInt(data[i]);
        }
        return LocalDate.of(dataINT[0], dataINT[1], dataINT[2]);
    }


    public String errorPage(){
        return "Error/Error";
    }

    public HttpSession resetSession(HttpSession session){
        session.removeAttribute("username");
        return session;
    }
}
