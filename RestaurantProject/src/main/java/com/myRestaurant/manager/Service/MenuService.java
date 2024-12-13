package com.myRestaurant.manager.Service;


import java.util.List;

import com.myRestaurant.manager.Entities.MenuEntities;

public interface MenuService {
    List<MenuEntities> getMenuItemsByType(String type);
}