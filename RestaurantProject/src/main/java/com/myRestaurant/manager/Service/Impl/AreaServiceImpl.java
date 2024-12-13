package com.myRestaurant.manager.Service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Dto.AreaDto;

public interface AreaServiceImpl {
	List<AreaDto> getAllAreas();
}
