package com.myRestaurant.manager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myRestaurant.manager.Entities.Book;

@Repository
public interface ViewBookRepository extends JpaRepository<Book, Integer> {
}