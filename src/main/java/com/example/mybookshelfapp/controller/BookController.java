package com.example.mybookshelfapp.controller;

import com.example.mybookshelfapp.dto.BookDTO;
import com.example.mybookshelfapp.dto.BookInDTO;
import com.example.mybookshelfapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        BookDTO bookDTO = bookService.getBook(id);

        if (bookDTO == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(bookDTO);
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

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {

        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
