package com.myRestaurant.manager.Views;

import com.myRestaurant.manager.Dto.MenuDto;
import com.myRestaurant.manager.Service.DeleteDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/dishes")
public class DeletemenuViews {

    @Autowired
    private DeleteDishService deleteDishService;

    // Trả về trang xóa món ăn
    @GetMapping
    public String getDeleteDishPage() {
        return "deleteDish";
    }

    // Tìm kiếm món ăn theo tên và trả về danh sách các món ăn để hiển thị trên giao diện
    @GetMapping("/search")
    public String searchDishes(@RequestParam("name") String dishName, Model model) {
        List<MenuDto> dishes = deleteDishService.searchDishesByName(dishName);
        model.addAttribute("dishes", dishes);
        return "deleteDish"; 
    }

}
