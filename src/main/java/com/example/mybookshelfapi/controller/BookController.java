package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.BookDTO;
import com.example.mybookshelfapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {

        List<BookDTO> bookDTOList = bookService.getAllBooks();
        if (bookDTOList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bookDTOList);
    }
}
