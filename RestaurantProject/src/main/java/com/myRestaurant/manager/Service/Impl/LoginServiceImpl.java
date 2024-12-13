package com.myRestaurant.manager.Service.Impl;

import java.util.List;

import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Payload.Request.SignupRequest;

public interface LoginServiceImpl {
	List<UserDto> getAllUser();
	boolean checkLogin(String username, String password);
	boolean addUser(SignupRequest signupRequest);
	UserDto getUserByUsername(String username);
}
