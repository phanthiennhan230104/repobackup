package com.myRestaurant.manager.Views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/website")
public class WebsiteView {
    @GetMapping("/myRestaurant")
    public String websiteView () {
        return "website"; 
    }
}
