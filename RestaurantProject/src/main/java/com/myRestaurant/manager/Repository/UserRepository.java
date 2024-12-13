package com.myRestaurant.manager.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.UserEntities;

@Repository
public interface UserRepository extends JpaRepository<UserEntities, Integer>{
	List<UserEntities> findByUsernameAndPassword(String username, String password);
	UserEntities findByUsername(String username);
	UserEntities findByPhoneNumber(String phonenumber);
	List<UserEntities> findByUsernameContainingIgnoreCase(String username);
	UserEntities findById(int id);
}
