package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.DishOrderDto;
import com.myRestaurant.manager.Payload.Request.DishOrderRequest;

import java.util.List;

public interface DishOrderService {
    List<DishOrderDto> getDishesByTable(String tableId);
}