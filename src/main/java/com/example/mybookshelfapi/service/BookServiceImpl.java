package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.BookDTO;
import com.example.mybookshelfapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(
                        b -> new BookDTO(
                                b.getTitle(),
                                b.getDescription(),
                                b.getAuthor(),
                                b.getPublicationDate(),
                                b.getIsbn(),
                                b.getLanguage(),
                                b.getPages()
                        )
                )
                .collect(Collectors.toList());
    }
}