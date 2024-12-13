package com.myRestaurant.manager.Views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomePageViews {
	@GetMapping("/homepage-user")
	public String userViews() {
		return "homepage";
	}
	@GetMapping("/homepage-admin")
	public String adminViews() {
		return "homepageadmin";
	}
	@GetMapping("/homepage-cashier")
	public String cashierViews() {
		return "homepagecashier";
	}
	@GetMapping("/homepage-chef")
	public String chefViews() {
		return "homepagechef";
	}
}
