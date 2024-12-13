package com.myRestaurant.manager.Views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@Controller
@RequestMapping("/login")
public class SigninViews {
	@GetMapping("/signin")
	public String showLoginPage() {
	    return "signin";
	}
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
}
