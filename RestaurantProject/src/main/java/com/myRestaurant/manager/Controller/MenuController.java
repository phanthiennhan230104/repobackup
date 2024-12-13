package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Entities.DishType;
import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Repository.MenuRepository;
import com.myRestaurant.manager.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/menu")
    public String getMenu(@RequestParam(name = "type", required = false) String type, Model model) {
        List<MenuEntities> menuItems;
        if (type != null && !type.isEmpty()) {
            try {
                menuItems = menuRepository.findByDishType(DishType.valueOf(type));
            } catch (IllegalArgumentException e) {
                menuItems = menuRepository.findAll(); 
            }
        } else {          
            menuItems = menuRepository.findAll();
        }
        model.addAttribute("menuItems", menuItems);
        return "menu";
    }
    
    
}