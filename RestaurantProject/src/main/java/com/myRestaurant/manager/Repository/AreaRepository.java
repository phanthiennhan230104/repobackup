package com.myRestaurant.manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.AreaEntities;

@Repository
public interface AreaRepository extends JpaRepository<AreaEntities, String>{
}
