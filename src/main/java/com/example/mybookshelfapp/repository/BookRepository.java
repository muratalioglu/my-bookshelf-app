package com.example.mybookshelfapp.repository;

import com.example.mybookshelfapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByDeleteTimeIsNull();

    Optional<Book> findByIdAndDeleteTimeIsNull(Integer id);

    boolean existsByTitleIgnoreCaseAndDeleteTimeIsNull(String title);

    List<Book> findByIdInAndDeleteTimeIsNull(Set<Integer> idSet);
}