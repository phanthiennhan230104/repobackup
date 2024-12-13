package com.myRestaurant.manager.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myRestaurant.manager.Dto.RoleDto;
import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Entities.RoleEntities;
import com.myRestaurant.manager.Entities.UserEntities;
import com.myRestaurant.manager.Repository.UserRepository;
import com.myRestaurant.manager.Service.Impl.UserServiceImpl;


@Service
public class UserService implements UserServiceImpl{
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<UserDto> getAllUser() {
		List<UserEntities> listuser = userRepository.findAll();
	    List<UserDto> userDtoList = new ArrayList<>();
	    
	    for (UserEntities user : listuser) {
	        UserDto userDto = new UserDto();
	        userDto.setId(user.getId());
	        userDto.setUsername(user.getUsername());
	        
	        // Chuyển role_id thành role_name
	        RoleDto roleDto = new RoleDto();
	        int roleId = user.getRoleEntities().getRole_id();
	        roleDto.setRole_id(roleId);

	        switch (roleId) {
	            case 0:
	                roleDto.setRole_name("Người dùng");
	                break;
	            case 1:
	                roleDto.setRole_name("Admin");
	                break;
	            case 2:
	                roleDto.setRole_name("Thu ngân");
	                break;
	            case 3:
	                roleDto.setRole_name("Đầu bếp");
	                break;
	            default:
	                roleDto.setRole_name("Chưa xác định");
	                break;
	        }

	        userDto.setRoleDto(roleDto);
	        userDtoList.add(userDto);
	    }
	    
	    return userDtoList;
	}

	@Override
	public List<UserDto> searchUsersByUsername(String username) {
		List<UserEntities> userEntitiesList = userRepository.findByUsernameContainingIgnoreCase(username);

        return userEntitiesList.stream().map(user -> {
            // Chuyển đổi UserEntities sang UserDto
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFullname(user.getFullname());
            userDto.setDateOfBirth(user.getDateOfBirth());
            userDto.setPhoneNumber(user.getPhoneNumber());
            userDto.setGender(user.isGender());
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword()); // Chú ý: không nên gửi password trong UserDto
            // Ánh xạ RoleEntities sang RoleDto
            RoleDto roleDto = new RoleDto();
            roleDto.setRole_id(user.getRoleEntities().getRole_id());
            roleDto.setRole_name(user.getRoleEntities().getRole_name());
            roleDto.setDescription(user.getRoleEntities().getDescription());
            userDto.setRoleDto(roleDto);

            return userDto;
        }).collect(Collectors.toList());
	}

	@Override
	public boolean deleteUser(int id) {
		if (userRepository.existsById(id)) {
	        userRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}
}
