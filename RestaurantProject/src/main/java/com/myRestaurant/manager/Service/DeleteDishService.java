package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.MenuDto;
import java.util.List;

public interface DeleteDishService {
    List<MenuDto> searchDishesByName(String dishName);
    boolean deleteDish(int dishId);
}
