package com.myRestaurant.manager.Views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myRestaurant.manager.Dto.UserDto;
import com.myRestaurant.manager.Service.UserService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/homepage-admin")
public class HomepageAdminViews {
	@Autowired
    private UserService userService;
	@GetMapping("/manage-account")
    public String managerAccountView(Model model) {
        List<UserDto> users = userService.getAllUser();
        model.addAttribute("users", users); // Truyền danh sách tài khoản vào model
        return "manageaccount"; // Tên file giao diện trong thư mục templates
    }
	@GetMapping("/add-account")
	public String addAccount() {
		return "addaccount";
	}
//	@GetMapping("/edit-account")
//	public String editAccount() {
//		return "userinformation";
//	}
}
