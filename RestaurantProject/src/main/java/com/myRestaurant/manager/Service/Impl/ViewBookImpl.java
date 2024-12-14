package com.myRestaurant.manager.Service.Impl;

import com.myRestaurant.manager.Entities.Book;
import com.myRestaurant.manager.Dto.ViewBookDto;
import com.myRestaurant.manager.Repository.ViewBookRepository;
import com.myRestaurant.manager.Service.ViewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewBookImpl implements ViewBookService {
    @Autowired
    private ViewBookRepository viewbookRepository;

    @Override
    public List<ViewBookDto> getAllBooks() {
        return viewbookRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ViewBookDto convertToDto(Book book) {
        ViewBookDto dto = new ViewBookDto();
        dto.setTableId(book.getTableId());
        dto.setNumberOfPeople(book.getNumberOfPeople());
        dto.setBookDate(book.getBookDate());
        dto.setBookTime(book.getBookTime());
        return dto;
    }
}

