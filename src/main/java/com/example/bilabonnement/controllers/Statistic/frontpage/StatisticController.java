package com.example.bilabonnement.controllers.Statistic.frontpage;

import com.example.bilabonnement.models.data.Rental;
import com.example.bilabonnement.repositories.rental.RentalManager;
import com.example.bilabonnement.services.ControllerService;
import com.example.bilabonnement.services.statistic.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;


@Controller
public class StatisticController {

    @GetMapping("/statistic")
    public String statistic(HttpSession session, Model model){
        RentalManager rentalManager = new RentalManager();
        StatisticService statisticService = new StatisticService();
        //monthly income
        double monthlyIncome = statisticService.calculateTotalCarPriceActive();
        model.addAttribute("incomemonthly", monthlyIncome);
        //income during
        if(session.getAttribute("income-during")==null){
            model.addAttribute("incomeduring", 0);
        } else {
            model.addAttribute("incomeduring", session.getAttribute("income-during"));
        }
        //active rentals
        ArrayList<Rental> activeRentals = rentalManager.getActiveRentals();
        model.addAttribute("rentals", activeRentals);
        return "Statistic/statistic-menu";
    }

    @PostMapping("/statistic-income-during-period")
    public String statisticMonthlyIncome(HttpSession session, WebRequest dataFromForm) {
        ControllerService controllerService = new ControllerService();
        StatisticService statisticService = new StatisticService();
        double income = statisticService.calculatePriceDuring(Date.valueOf(controllerService.generateDate(dataFromForm.getParameter("start_date"))),
                Date.valueOf(controllerService.generateDate(dataFromForm.getParameter("end_date"))));
        session.setAttribute("income-during", income);
        return "redirect:/statistic";
    }
}
