package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByDeleteTimeIsNull();

    Optional<Book> findByIdAndDeleteTimeIsNull(Integer id);

    boolean existsByTitleIgnoreCase(String title);
}