package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.Dto.TableDto;
import com.myRestaurant.manager.Entities.TableEntities;

public interface TableServiceImpl {
	TableEntities addTable(TableDto tableDto);
	boolean updateTableStatus(String tableId, boolean isAvailable);
}
