package com.myRestaurant.manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}