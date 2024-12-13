package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Repository.MenuItemRepository;
import com.myRestaurant.manager.Service.DeleteDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeleteDishServiceImpl implements DeleteDishService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/assets/img/";
    
    @Override
    public List<MenuDto> searchDishesByName(String name) {
        List<MenuEntities> dishes = menuItemRepository.findByDishNameContainingIgnoreCase(name);
        return dishes.stream().map(dish -> {
            MenuDto menuDto = new MenuDto();
            menuDto.setDishId(dish.getDishId());
            menuDto.setDishName(dish.getDishName());
            menuDto.setDishType(dish.getDishType());  
            menuDto.setPrice(dish.getPrice());
            menuDto.setDescription(dish.getDescription());
            menuDto.setImagePath(dish.getImagePath());
            return menuDto;
        }).collect(Collectors.toList());
    }
    
    @Override
    public boolean deleteDish(int dishId) {
        MenuEntities dish = menuItemRepository.findById(dishId).orElse(null);
        
        if (dish == null) {
            System.out.println("Dish not found: " + dishId);
            return false;
        }

        // Xóa hình ảnh nếu có
        String imagePath = dish.getImagePath();
        if (imagePath != null) {
            Path path = Paths.get(IMAGE_UPLOAD_DIR + imagePath.replace("/assets/img/", ""));
            try {
                Files.delete(path);
                System.out.println("Image deleted successfully: " + path.toString());
            } catch (IOException e) {
                System.out.println("Error deleting image: " + path.toString());
                e.printStackTrace();
                return false;
            }
        }

        // Xóa món ăn khỏi cơ sở dữ liệu
        menuItemRepository.delete(dish);
        System.out.println("Dish deleted successfully: " + dishId);
        return true;
    }


}
