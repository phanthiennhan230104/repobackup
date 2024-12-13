package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.*;
import com.myRestaurant.manager.Dto.BookDto;
import com.myRestaurant.manager.Entities.Book;
import com.myRestaurant.manager.Repository.BookRepository;
import com.myRestaurant.manager.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<Map<String, Object>> saveBook(BookDto bookDto) {
        // Tạo đối tượng OrderEntities từ OrderDto
        Book bookEntity = new Book();
        bookEntity.setTableId(bookDto.getTableId());
        bookEntity.setNumberOfPeople(bookDto.getNumberOfPeople());
        bookEntity.setBookDate(bookDto.getBookDate());
        bookEntity.setBookTime(bookDto.getBookTime());

        // Lưu đơn hàng vào cơ sở dữ liệu
        bookRepository.save(bookEntity);
        
        // Tạo một Map để chứa thông tin phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("message", "Your booking was successful!");
        
        // Đảm bảo trả về đầy đủ các thông tin chi tiết
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("Booking ID", bookEntity.getBookId());
        bookingDetails.put("Table ID", bookEntity.getTableId());
        bookingDetails.put("Number of People", bookEntity.getNumberOfPeople());
        bookingDetails.put("Booking Date", bookEntity.getBookDate());
        bookingDetails.put("Booking Time", bookEntity.getBookTime());
        
        response.put("bookingDetails", bookingDetails);

        // Trả về phản hồi với mã trạng thái HTTP
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
