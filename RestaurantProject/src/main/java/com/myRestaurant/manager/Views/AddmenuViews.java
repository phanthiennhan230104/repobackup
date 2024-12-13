package com.myRestaurant.manager.Views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Payload.Request.AddMenuItemRequest;

@Controller
@RequestMapping("/menu")
public class AddmenuViews {
	@GetMapping("/add")
	public String showAddMenuItemForm(Model model) {
	    model.addAttribute("menuItem", new MenuEntities());
	    return "AddMenuItem"; 
	}
}
