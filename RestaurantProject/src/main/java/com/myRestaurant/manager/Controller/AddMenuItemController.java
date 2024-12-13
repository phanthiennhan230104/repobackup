package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Payload.Response.AddMenuItemResponse;
import com.myRestaurant.manager.Payload.Request.AddMenuItemRequest;
import com.myRestaurant.manager.Service.AddMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/menu")
public class AddMenuItemController {

    @Autowired
    private AddMenuService menuService;

    @PostMapping("/add")
    public String addMenuItem(@ModelAttribute AddMenuItemRequest request,
                              @RequestParam("image") MultipartFile image,
                              Model model) {
        try {
            MenuDto addedItem = menuService.addMenuItem(request, image);

            AddMenuItemResponse response = new AddMenuItemResponse();
            response.setDishId(addedItem.getDishId());
            response.setDishName(addedItem.getDishName());
            response.setDishType(addedItem.getDishType());
            response.setPrice(addedItem.getPrice());
            response.setDescription(addedItem.getDescription());
            response.setImagePath(addedItem.getImagePath());

            model.addAttribute("successMessage", "Menu added successfully!");
            model.addAttribute("menuEntities", response);

            return "AddMenuItem";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to add menu. Please try again!"+ e.getMessage());
            return "AddMenuItem";
        }
    }
}
