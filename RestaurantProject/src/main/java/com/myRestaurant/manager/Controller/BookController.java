package com.myRestaurant.manager.Controller;

import com.myRestaurant.manager.Dto.BookDto;
import com.myRestaurant.manager.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/website")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> submitBooking(@RequestParam("table_id") String tableId,
                                                              @RequestParam("number_of_people") int numberOfPeople,
                                                              @RequestParam("book_date") String bookDate,
                                                              @RequestParam("book_time") String bookTime) {
        // Chuyển dữ liệu từ form thành OrderDto
    	BookDto bookDto = new BookDto();
    	
    	bookDto.setTableId(tableId);
    	bookDto.setNumberOfPeople(numberOfPeople);
    	bookDto.setBookDate(bookDate);
    	bookDto.setBookTime(bookTime);

        // Lưu thông tin vào cơ sở dữ liệu qua service và trả về kết quả
        ResponseEntity<Map<String, Object>> response = bookService.saveBook(bookDto);
        return response; // Trả về kết quả từ service
    }
}