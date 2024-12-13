package com.myRestaurant.manager.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myRestaurant.manager.Entities.UserEntities;
import com.myRestaurant.manager.Service.UserService;
import com.myRestaurant.manager.Service.UserServices;


@Controller
public class UserController {
 
    @Autowired
    private UserServices service;
     
    // handler methods...
    @RequestMapping("/user")
    public String viewHomePage(Model model) {
        List<UserEntities> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "listuser";
    }
    @RequestMapping("/point")
    public String viewPoint(Model model) {
        List<UserEntities> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "listpoint";
    }
    @RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") UserEntities user) {
    	
        UserEntities existingUser = service.get(user.getId());
        
        existingUser.setFullname(user.getFullname());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setPassword(user.getPassword());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setRoleEntities(user.getRoleEntities());
        
        service.save(existingUser);
        return "redirect:/user";  // Quay lại trang danh sách người dùng
    }
    @RequestMapping("/edituser/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") int id) {
    ModelAndView mav = new ModelAndView("editaccount");
    UserEntities user = service.get(id);
    mav.addObject("user", user);
    return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/user";       
    }
}
