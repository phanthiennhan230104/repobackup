package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Service.ViewOrderListService;
import com.myRestaurant.manager.Dto.ViewOrderListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewOrderListController {

    @Autowired
    private ViewOrderListService viewOrderListService;

    @GetMapping("/chef/tables")
    public String getTables(Model model) {
        List<ViewOrderListDto> invoices = viewOrderListService.getInvoicesWithTables();
        model.addAttribute("invoices", invoices);
        return "ViewTableOrderList";
    }
}
