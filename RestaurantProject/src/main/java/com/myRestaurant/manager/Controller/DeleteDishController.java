package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Payload.Response.DeleteDishResponse;
import com.myRestaurant.manager.Payload.Request.DeleteDishRequest;
import com.myRestaurant.manager.Service.DeleteDishService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deleteDish")
public class DeleteDishController {

    @Autowired
    private DeleteDishService deleteDishService;

    @GetMapping("/autocomplete")
    public List<MenuDto> autocompleteDishes(@RequestParam("name") String dishName) {
        return deleteDishService.searchDishesByName(dishName);
    }
    
    @PostMapping
    public DeleteDishResponse deleteDish(@RequestBody DeleteDishRequest request) {
        boolean isDeleted = deleteDishService.deleteDish(request.getDishId());

        if (isDeleted) {
            return new DeleteDishResponse("Dish deleted successfully", null);
        } else {
            return new DeleteDishResponse(null, "Failed to delete the dish");
        }
    }
}
