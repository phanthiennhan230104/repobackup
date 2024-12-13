package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Entities.InvoiceEntities;
import com.myRestaurant.manager.Dto.RevenueReportDTO;
import com.myRestaurant.manager.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevenueReportService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<RevenueReportDTO> getRevenueReport() {
        List<InvoiceEntities> invoices = invoiceRepository.findByInvoiceStatus(true);
        List<RevenueReportDTO> report = new ArrayList<>();

        for (InvoiceEntities invoice : invoices) {
            invoice.getInvoiceMenuEntities().forEach(menuItem -> {
                String dishName = menuItem.getMenuEntities().getDishName();
                BigDecimal price = menuItem.getMenuEntities().getPrice();
                int quantity = menuItem.getQuantity();
                BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(quantity));
                report.add(new RevenueReportDTO(dishName, price, quantity, totalPrice));
            });
        }
        return report;
    }
}
