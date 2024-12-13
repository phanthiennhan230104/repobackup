package com.myRestaurant.manager.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Dto.RoleDto;
import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Entities.RoleEntities;
import com.myRestaurant.manager.Entities.UserEntities;
import com.myRestaurant.manager.Payload.Request.SignupRequest;
import com.myRestaurant.manager.Repository.UserRepository;
import com.myRestaurant.manager.Service.Impl.LoginServiceImpl;

@Service
public class LoginService implements LoginServiceImpl{
	@Autowired
	UserRepository userRepository; 
	
	@Override
	public List<UserDto> getAllUser(){
		List<UserEntities> listuser = userRepository.findAll();
		List<UserDto> userDtoList = new ArrayList<>();
		for(UserEntities user : listuser) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFullname(user.getFullname());
			userDto.setDateOfBirth(user.getDateOfBirth());
			userDto.setPhoneNumber(user.getPhoneNumber());
			userDto.setGender(user.isGender());
			userDto.setUsername(user.getUsername());
			userDto.setPassword(user.getPassword());
			
			//Thêm roleDto để truy xuất role_id
			RoleDto roleDto = new RoleDto();
			roleDto.setRole_id(user.getRoleEntities().getRole_id());
			roleDto.setRole_name(user.getRoleEntities().getRole_name());
			roleDto.setDescription(user.getRoleEntities().getDescription());
			userDto.setRoleDto(roleDto);
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public boolean checkLogin(String username, String password) {
		List<UserEntities> listUser = userRepository.findByUsernameAndPassword(username, password);
		return listUser.size() > 0;
	}

	@Override
	public boolean addUser(SignupRequest signupRequest) {
		try {
	        // Kiểm tra phonenumber hoặc username đã tồn tại
	        UserEntities existingUserByPhone = userRepository.findByPhoneNumber(signupRequest.getPhonenumber());
	        UserEntities existingUserByUsername = userRepository.findByUsername(signupRequest.getUsername());
	        if (existingUserByPhone != null) {
	            throw new IllegalArgumentException("Phone number already exists.");
	        }
	        if (existingUserByUsername != null) {
	            throw new IllegalArgumentException("Username already exists.");
	        }
	        if (!signupRequest.getPhonenumber().matches("\\d{10,11}")) {
	            throw new IllegalArgumentException("Phone number must be 10 to 11 digits and contain no letters.");
	        }
	        if (!signupRequest.getUsername().matches("[a-zA-Z0-9]{6,}")) {
	            throw new IllegalArgumentException("Username must be at least 6 characters long and contain no special characters.");
	        }
	        RoleEntities roleEntities = new RoleEntities();
	        roleEntities.setRole_id(0);

	        UserEntities userEntity = new UserEntities();
	        userEntity.setFullname(signupRequest.getFullname());
	        userEntity.setDateOfBirth(signupRequest.getDateofbirth());
	        userEntity.setPhoneNumber(signupRequest.getPhonenumber());
	        userEntity.setGender(signupRequest.isGender());
	        userEntity.setUsername(signupRequest.getUsername());
	        userEntity.setPassword(signupRequest.getPassword());
	        userEntity.setRoleEntities(roleEntities);
	        userRepository.save(userEntity);
	        return true;
	    } catch (IllegalArgumentException e) {
	        System.err.println("Validation Error: " + e.getMessage());
	        throw e;
	    } catch (Exception e) {
	        System.err.println("Unexpected Error: " + e.getMessage());
	        throw e;
	    }

	    // Tạo đối tượng UserEntities
//	    RoleEntities roleEntities = new RoleEntities();
//	    roleEntities.setRole_id(0);
//
//	    UserEntities userentity = new UserEntities();
//	    userentity.setFullname(signupRequest.getFullname());
//	    userentity.setDateOfBirth(signupRequest.getDateofbirth());
//	    userentity.setPhoneNumber(signupRequest.getPhonenumber());
//	    userentity.setGender(signupRequest.isGender());
//	    userentity.setUsername(signupRequest.getUsername());
//	    userentity.setPassword(signupRequest.getPassword());
//	    userentity.setRoleEntities(roleEntities);
//
//	    try {
//	        userRepository.save(userentity);
//	        return true;
//	    } catch (Exception e) {
//	        return false;
//	    }
	}

	@Override
	public UserDto getUserByUsername(String username) {
		UserEntities userEntity = userRepository.findByUsername(username);
	    if (userEntity == null) return null;
	    UserDto userDto = new UserDto();
	    userDto.setId(userEntity.getId());
	    userDto.setFullname(userEntity.getFullname());
	    userDto.setDateOfBirth(userEntity.getDateOfBirth());
	    userDto.setPhoneNumber(userEntity.getPhoneNumber());
	    userDto.setGender(userEntity.isGender());
	    userDto.setUsername(userEntity.getUsername());
	    userDto.setPassword(userEntity.getPassword());
	    
	    RoleDto roleDto = new RoleDto();
	    roleDto.setRole_id(userEntity.getRoleEntities().getRole_id());
	    roleDto.setRole_name(userEntity.getRoleEntities().getRole_name());
	    roleDto.setDescription(userEntity.getRoleEntities().getDescription());
	    userDto.setRoleDto(roleDto);
	    return userDto;
	}
}
