package com.myRestaurant.manager.Views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage-user")
public class HomepageUserViews{
	@GetMapping("/choose-table")
	public String userViews() {
		return "choosetable";
	}
	@GetMapping("/menu")
	public String adminViews() {
		return "menu";
	}
	@GetMapping("/invoice")
	public String cashierViews() {
		return "seeinvoice";
	}
	@GetMapping("/points")
	public String chefViews() {
		return "homepagechef";
	}
}
