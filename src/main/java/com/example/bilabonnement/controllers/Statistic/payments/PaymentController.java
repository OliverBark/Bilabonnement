package com.example.bilabonnement.controllers.Statistic.payments;

import com.example.bilabonnement.models.economy.Payment;
import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.payment.PaymentManager;
import com.example.bilabonnement.repositories.sale_record.SaleRecordManager;
import com.example.bilabonnement.services.ControllerService;
import com.example.bilabonnement.services.statistic.MonthlyFeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

@Controller
public class PaymentController {
    ControllerService controllerService = new ControllerService();

    @GetMapping("/statistic-register-new-month")
    public String registerNewMonth(){
        MonthlyFeeService monthlyFeeService = new MonthlyFeeService();
        monthlyFeeService.newMonth();
        return "redirect:/statistic-view-payments";
    }

    @GetMapping("/statistic-view-payments")
    public String viewPendingPayments(HttpSession session, Model model){
        getPayments(session);
        model.addAttribute("payments", session.getAttribute("payments"));
        return "Statistic/view-payments";
    }

    @GetMapping("/create-new-payment")
    public String createPayment(HttpSession session){
        return "Statistic/create-payment";
    }

    @PostMapping("/creating-payment")
    public String createPayment(HttpSession session, WebRequest dataFromForm){
        ControllerService controllerService = new ControllerService();
        PaymentManager paymentManager = new PaymentManager();

        Payment payment = new Payment(0, Double.parseDouble(dataFromForm.getParameter("amount")),
                Date.valueOf(controllerService.generateDate(dataFromForm.getParameter("date"))),
                dataFromForm.getParameter("type"),
                Integer.parseInt(dataFromForm.getParameter("rental_id")));
        try {
            paymentManager.createPayment(payment);
        } catch (Exception e) {
            System.out.println("error in input");
        }
        return "redirect:/statistic-view-payments";
    }

    @PostMapping("/confirm-payment")
    public String confirmPayment(WebRequest dataFromForm){
        PaymentManager paymentManager = new PaymentManager();
        SaleRecordManager saleRecordManager = new SaleRecordManager();
        System.out.println("managers");
        Payment payment = paymentManager.getPayment(Integer.parseInt(dataFromForm.getParameter("payment_id")));
        System.out.println("managers2");
        SaleRecord saleRecord = new SaleRecord(0, payment.getAmount(),
                dataFromForm.getParameter("type"),
                Date.valueOf(controllerService.generateDate(dataFromForm.getParameter("date"))),
                payment.getRentalId());
        System.out.println("salerecord made");
        saleRecordManager.createSaleRecord(saleRecord);
        System.out.println("creating sale record");
        paymentManager.deletePayment(payment.getPaymentId());
        System.out.println("deleting payment");
        return "redirect:/statistic-view-payments";
    }

    private void getPayments(HttpSession session){
        PaymentManager paymentManager = new PaymentManager();
        ArrayList<Payment> payments = paymentManager.getPaymentList();
        session.setAttribute("payments", payments);
    }
}
