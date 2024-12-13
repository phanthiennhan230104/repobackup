package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.RevenueReportDTO;
import com.myRestaurant.manager.Service.RevenueReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class RevenueReportController {

    @Autowired
    private RevenueReportService revenueReportService;

    @GetMapping("/homepage-cashier/report")
    public String getRevenueReport(Model model) {
        List<RevenueReportDTO> revenueReports = revenueReportService.getRevenueReport();
        BigDecimal totalAmount = revenueReports.stream()
                .map(RevenueReportDTO::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("revenueReports", revenueReports);
        model.addAttribute("totalAmount", totalAmount); // Đảm bảo biến này có mặt

        return "report";
    }
}
