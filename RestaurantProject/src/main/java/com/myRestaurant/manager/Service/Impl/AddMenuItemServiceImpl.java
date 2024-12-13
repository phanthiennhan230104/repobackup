package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Entities.DishType;
import com.myRestaurant.manager.Payload.Request.AddMenuItemRequest;
import com.myRestaurant.manager.Repository.MenuItemRepository;
import com.myRestaurant.manager.Service.AddMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Service
public class AddMenuItemServiceImpl implements AddMenuService {

    private static final String IMAGE_UPLOAD_DIR = "src/main/resources/static/assets/img/";

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public MenuDto addMenuItem(AddMenuItemRequest request, MultipartFile image) {
        String imagePath = saveImage(image);

        MenuEntities menuItem = new MenuEntities();
        menuItem.setDishName(request.getDishName());
        menuItem.setDishType(DishType.fromString(request.getDishType()));
        menuItem.setPrice(request.getPrice());
        menuItem.setDescription(request.getDescription());
        menuItem.setImagePath(imagePath);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        menuItem.setCreatedAt(timestamp);
        menuItem.setUpdatedAt(timestamp);

        MenuEntities savedMenuItem = menuItemRepository.save(menuItem);

        return mapToDto(savedMenuItem);
    }

    private String saveImage(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(IMAGE_UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("Invalid file name");
            }

            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath);

            return "/assets/img/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
    }


    private MenuDto mapToDto(MenuEntities menuItem) {
        MenuDto dto = new MenuDto();
        dto.setDishId(menuItem.getDishId());
        dto.setDishName(menuItem.getDishName());
        dto.setDishType(menuItem.getDishType());
        dto.setPrice(menuItem.getPrice());
        dto.setDescription(menuItem.getDescription());
        dto.setImagePath(menuItem.getImagePath());
        dto.setCreatedAt(menuItem.getCreatedAt());
        dto.setUpdatedAt(menuItem.getUpdatedAt());
        return dto;
    }
}