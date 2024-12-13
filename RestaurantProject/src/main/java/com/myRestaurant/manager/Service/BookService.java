package com.myRestaurant.manager.Service;

import com.myRestaurant.manager.Dto.BookDto;
import org.springframework.http.ResponseEntity;
import java.util.Map;

public interface BookService {
    ResponseEntity<Map<String, Object>> saveBook(BookDto bookDto);
}
