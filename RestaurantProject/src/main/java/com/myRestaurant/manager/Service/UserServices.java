package com.myRestaurant.manager.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Entities.UserEntities;
import com.myRestaurant.manager.Repository.UserRepository;

import jakarta.transaction.Transactional;
import java.util.Optional;
@Service
@Transactional
public class UserServices {

	@Autowired
	private UserRepository repo;

	public List<UserEntities> listAll() {
		return repo.findAll();
	}

	public void save(UserEntities user) {
		repo.save(user);
	}

	public UserEntities get(int id) {
		return repo.findById((int) id);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

}

