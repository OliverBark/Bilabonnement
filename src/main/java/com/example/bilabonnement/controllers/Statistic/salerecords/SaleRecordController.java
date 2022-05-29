package com.example.bilabonnement.controllers.Statistic.salerecords;

import com.example.bilabonnement.models.economy.SaleRecord;
import com.example.bilabonnement.repositories.sale_record.SaleRecordManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class SaleRecordController {

    @GetMapping("/statistic-view-sale-records")
    public String viewSaleRecords(HttpSession session, Model model){
        getSaleRecords(session);
        model.addAttribute("salerecords", session.getAttribute("sale-records"));
        return "Statistic/view-sale-records";
    }

    private void getSaleRecords(HttpSession session){
        SaleRecordManager saleRecordManager = new SaleRecordManager();
        ArrayList<SaleRecord> records = saleRecordManager.getSaleRecordList();

        Collections.sort(records, new Comparator<SaleRecord>() {
            @Override
            public int compare(SaleRecord o1, SaleRecord o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        session.setAttribute("sale-records", records);
    }
}
