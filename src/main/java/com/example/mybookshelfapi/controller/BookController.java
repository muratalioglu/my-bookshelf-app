package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.BookDTO;
import com.example.mybookshelfapi.dto.BookInDTO;
import com.example.mybookshelfapi.service.BookService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
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

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Integer id) {

        return ResponseEntity.ok(bookService.getBook(id));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody @Valid BookInDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("BookId", bookService.createBook(dto).toString())
                .build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String description,
                                        @RequestParam(required = false) String author,
                                        @RequestParam(required = false) String isbn,
                                        @RequestParam(required = false) String language,
                                        @RequestParam(required = false) @Positive Integer publicationYear,
                                        @RequestParam(required = false) @Positive Integer pages) {

        bookService.updateBook(
                id,
                title,
                description,
                author,
                isbn,
                language,
                publicationYear,
                pages
        );

        return ResponseEntity.noContent().build();
    }
}
