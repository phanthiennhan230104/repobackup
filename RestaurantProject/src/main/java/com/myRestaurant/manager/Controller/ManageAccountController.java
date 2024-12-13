package com.myRestaurant.manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Payload.ResponseData;
import com.myRestaurant.manager.Payload.Request.SignupRequest;
import com.myRestaurant.manager.Service.UserService;
import com.myRestaurant.manager.Service.Impl.LoginServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/homepage-admin")
public class ManageAccountController {
	@Autowired
	LoginServiceImpl loginServiceImpl;
	
	@Autowired
    UserService userService;
	@PostMapping("/add-account")
	public ResponseEntity<?> addAccount(@RequestBody SignupRequest signupRequest){
		 ResponseData responseData = new ResponseData();
		    try {
		        boolean isAdded = loginServiceImpl.addUser(signupRequest);
		        responseData.setData(isAdded);
		        responseData.setDescription("Add account successful!");
		        return new ResponseEntity<>(responseData, HttpStatus.OK);
		    } catch (IllegalArgumentException e) {
		        responseData.setStatus(400); // Bad Request
		        responseData.setDescription(e.getMessage());
		        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
		    } catch (Exception e) {
		        responseData.setStatus(500); // Internal Server Error
		        responseData.setDescription("An unexpected error occurred. Please try again.");
		        return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@GetMapping("/manage-account/list-user")
	public List<UserDto> getUsers() {
        return userService.getAllUser();
    }
	
	// Tìm kiếm người dùng theo username
    @GetMapping("/manage-account/search")
    public List<UserDto> searchUsers(@RequestParam String username) {
        return userService.searchUsersByUsername(username);
    }
    
    @DeleteMapping("/manage-account/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        ResponseData responseData = new ResponseData();
        try {
            boolean isDeleted = userService.deleteUser(id);
            responseData.setData(isDeleted);
            responseData.setDescription(isDeleted ? "Delete account successful!" : "Account not found!");
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (Exception e) {
            responseData.setStatus(500); // Internal Server Error
            responseData.setDescription("An unexpected error occurred. Please try again.");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
