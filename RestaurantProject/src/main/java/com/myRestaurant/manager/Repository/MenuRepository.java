package com.myRestaurant.manager.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.MenuEntities;
import com.myRestaurant.manager.Entities.DishType;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntities, Integer> {
	 List<MenuEntities> findByDishType(DishType dishType);
}