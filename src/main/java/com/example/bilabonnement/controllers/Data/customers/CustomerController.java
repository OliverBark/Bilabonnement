package com.example.bilabonnement.controllers.Data.customers;

import com.example.bilabonnement.models.data.Customer;
import com.example.bilabonnement.repositories.customer.CustomerManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @GetMapping("/data-view-customers")
    public String viewCustomers(Model model){
        CustomerManager customerManager = new CustomerManager();
        model.addAttribute("customers", customerManager.getCustomerList());
        System.out.println("started");
        return "Data/view-customers";
    }

    @GetMapping("/data-edit-customer")
    public String editCustomer(HttpSession session, Model model){
        if(session.getAttribute("customer")==null){
            return "redirect:/data-view-customers";
        }
        updateCustomer(session);
        model.addAttribute("customer", session.getAttribute("customer"));
        return "Data/edit-customer";
    }

    @PostMapping("/data-choose-customer")
    public String chooseCustomer(HttpSession session, WebRequest dataFromForm){
        CustomerManager customerManager = new CustomerManager();
        Customer customer = customerManager.getCustomer(dataFromForm.getParameter("customer_cpr"));
        session.setAttribute("customer", customer);
        return "redirect:/data-edit-customer";
    }

    @PostMapping("/data-editing-customer")
    public String editingCustomer(HttpSession session, WebRequest dataFromForm){
        CustomerManager customerManager = new CustomerManager();
        String cprNr = ((Customer) session.getAttribute("customer")).getCprNr();
        if(dataFromForm.getParameter("first_name")!=null){
            String firstName = dataFromForm.getParameter("first_name");
            customerManager.updateCustomer(cprNr, "first_name", firstName);
        }
        if(dataFromForm.getParameter("last_name")!=null){
            String lastName = dataFromForm.getParameter("last_name");
            customerManager.updateCustomer(cprNr, "last_name", lastName);
        }
        if(dataFromForm.getParameter("mobile")!=null){
            String mobile = dataFromForm.getParameter("mobile");
            customerManager.updateCustomer(cprNr, "mobile", mobile);
        }
        return "redirect:/data-edit-customer";
    }

    private void updateCustomer(HttpSession session){
        CustomerManager customerManager = new CustomerManager();
        Customer customer = (Customer) session.getAttribute("customer");
        Customer updatedCustomer = customerManager.getCustomer(customer.getCprNr());
        session.setAttribute("customer", updatedCustomer);
    }

}
