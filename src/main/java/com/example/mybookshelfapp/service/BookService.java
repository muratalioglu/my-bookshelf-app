package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.BookDTO;
import com.example.mybookshelfapp.dto.BookInDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBook(Integer id);

    Integer createBook(BookInDTO dto);

    void updateBook(Integer id, String title, String description, String author,
                    String isbn, String language, Integer publicationYear, Integer pages);

    void deleteBook(Integer id);
}