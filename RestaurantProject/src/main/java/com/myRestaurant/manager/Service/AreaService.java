package com.myRestaurant.manager.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Dto.AreaDto;
import com.myRestaurant.manager.Entities.AreaEntities;
import com.myRestaurant.manager.Repository.AreaRepository;
import com.myRestaurant.manager.Service.Impl.AreaServiceImpl;

@Service
public class AreaService implements AreaServiceImpl{
	@Autowired
	AreaRepository areaRepository;
	
	@Override
	public List<AreaDto> getAllAreas() {
		List<AreaEntities> areaEntities = areaRepository.findAll();
		List<AreaDto> areaDtos = new  ArrayList<AreaDto>();
		for(AreaEntities area : areaEntities) {
			AreaDto areaDto = new AreaDto();
			areaDto.setArea_id(area.getAreaId());
			areaDto.setArea_name(area.getAreaName());
			areaDtos.add(areaDto);
		}
		return areaDtos;
	}
}
