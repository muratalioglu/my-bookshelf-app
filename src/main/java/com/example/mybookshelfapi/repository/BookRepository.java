package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByTitleIgnoreCase(String title);
}