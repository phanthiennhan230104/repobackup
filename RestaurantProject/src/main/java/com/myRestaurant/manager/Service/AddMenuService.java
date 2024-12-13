package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Payload.Request.AddMenuItemRequest;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface AddMenuService {
    MenuDto addMenuItem(AddMenuItemRequest request, MultipartFile image);
//    List<MenuEntities> getMenuItemsByType(String type);
}
