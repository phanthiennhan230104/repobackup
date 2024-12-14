package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.DishOrderDto;
import com.myRestaurant.manager.Payload.Request.DishOrderRequest;
import com.myRestaurant.manager.Payload.Response.DishOrderResponse;
import com.myRestaurant.manager.Service.DishOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DishOrderController {

    @Autowired
    private DishOrderService dishOrderService;

    // Get dishes by table ID
    @GetMapping("/{tableId}")
    public String getDishesByTable(@PathVariable String tableId, Model model) {
        List<DishOrderDto> dishes = dishOrderService.getDishesByTable(tableId);
        model.addAttribute("dishes", dishes);
        model.addAttribute("tableId", tableId);
        return "DishOrder";  // Return view to display the dishes
    }
}

