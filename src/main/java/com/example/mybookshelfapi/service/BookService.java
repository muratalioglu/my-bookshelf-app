package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
}