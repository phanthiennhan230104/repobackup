package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.Dto.DishOrderDto;
import com.myRestaurant.manager.Entities.InvoiceMenuEntities;
import com.myRestaurant.manager.Payload.Request.DishOrderRequest;
import com.myRestaurant.manager.Repository.DishOrderRepository;
import com.myRestaurant.manager.Repository.InvoiceMenuRepository;
import com.myRestaurant.manager.Service.DishOrderService;
import com.myRestaurant.manager.Entities.MenuEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DishOrderServiceImpl implements DishOrderService {

    @Autowired
    private DishOrderRepository dishOrderRepository;

    @Override
    public List<DishOrderDto> getDishesByTable(String tableId) {
        List<InvoiceMenuEntities> dishes = dishOrderRepository.findByInvoiceEntities_table_tableId(tableId);

        return dishes.stream()
                .map(dish -> new DishOrderDto(
                        dish.getId().getDishId(),
                        dish.getMenuEntities().getDishName(),
                        dish.getQuantity(),
                        dish.getMenuEntities().getPrice()
                ))
                .collect(Collectors.toList());
    }
}



