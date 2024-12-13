package com.myRestaurant.manager.Views;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myRestaurant.manager.Dto.InvoiceDetailDto;
import com.myRestaurant.manager.Dto.InvoiceDto;
import com.myRestaurant.manager.Service.InvoiceService;

@Controller
@RequestMapping("/homepage-cashier")
public class HomePageCashierViews {
	@Autowired
    private InvoiceService invoiceService;
	
	@GetMapping("/pay")
    public String payViews(Model model) {
        // Fetch all invoices
        List<InvoiceDto> invoices = invoiceService.getAllInvoices();
        model.addAttribute("invoices", invoices);  // Add invoices to model for rendering
        return "pay";  // Return the "pay" view (HTML template)
    }
	
	@GetMapping("/delete-table")
	public String deleteTableViews() {
		return "deletetable";
	}
	@GetMapping("/add-table")
	public String addTableViews() {
		return "addtable";
	}
	@GetMapping("/invoice-pay/detail-invoice/{invoiceId}")
	public String showInvoiceDetailsPage(@PathVariable int invoiceId, Model model) {
	    List<InvoiceDetailDto> details = invoiceService.getInvoiceDetails(invoiceId);
	    model.addAttribute("invoiceDetails", details);
	    return "detailsinvoice";
	}
}
