package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.ViewBookDto;
import com.myRestaurant.manager.Service.ViewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewBookController {
    @Autowired
    private ViewBookService viewBookService;

    @GetMapping("/viewBook")
    public String getBooks(Model model) {
        List<ViewBookDto> books = viewBookService.getAllBooks();
        model.addAttribute("books", books);
        return "viewBook";
    }
}
