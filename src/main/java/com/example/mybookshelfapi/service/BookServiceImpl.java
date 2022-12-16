package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.BookDTO;
import com.example.mybookshelfapi.dto.BookInDTO;
import com.example.mybookshelfapi.entity.Book;
import com.example.mybookshelfapi.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
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
                        book -> new BookDTO(
                                book.getId(),
                                book.getTitle(),
                                book.getDescription(),
                                book.getAuthor(),
                                book.getIsbn(),
                                book.getLanguage(),
                                book.getPublicationYear(),
                                book.getPages()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBook(Integer id) {

        Book book = getBookById(id);

        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getIsbn(),
                book.getLanguage(),
                book.getPublicationYear(),
                book.getPages()
        );
    }

    /**
     * Retrieves the Book record from database by id.
     *
     * @param id Book id
     * @return Book
     *
     * @throws ResponseStatusException If no Book found with the given id
     */
    private Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No Book found with given id")
                );
    }

    @Override
    public Integer createBook(BookInDTO dto) {

        String title = validateString(dto.getTitle());

        if (bookRepository.existsByTitleIgnoreCase(title))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("Book with title %s already exists!", title)
            );

        String description = validateString(dto.getDescription());
        String author = validateString(dto.getAuthor());
        String isbn = validateString(dto.getIsbn());
        String language = validateString(dto.getLanguage());

        Book book = new Book(
                title,
                description,
                author,
                isbn,
                language,
                dto.getPublicationYear(),
                dto.getPages()
        );

        bookRepository.save(book);

        return book.getId();
    }

    @Override
    public void updateBook(Integer id, String title, String description, String author,
                           String isbn, String language, Integer publicationYear, Integer pages) {

        Book book = getBookById(id);

        boolean updated = false;

        title = validateString(title);
        if (title != null && !title.equals(book.getTitle())) {
            book.setTitle(title);
            updated = true;
        }

        description = validateString(description);
        if (description != null && !description.equals(book.getDescription())) {
            book.setDescription(description);
            updated = true;
        }

        author = validateString(author);
        if (author != null && !author.equals(book.getAuthor())) {
            book.setAuthor(author);
            updated = true;
        }

        isbn = validateString(isbn);
        if (isbn != null && !isbn.equals(book.getIsbn())) {
            book.setIsbn(isbn);
            updated = true;
        }

        language = validateString(language);
        if (language != null && !language.equals(book.getLanguage())) {
            book.setLanguage(language);
            updated = true;
        }

        if (publicationYear != null && !publicationYear.equals(book.getPublicationYear())) {
            book.setPublicationYear(publicationYear);
            updated = true;
        }

        if (pages != null && !pages.equals(book.getPages())) {
            book.setPages(pages);
            updated = true;
        }

        if (updated)
            bookRepository.save(book);
    }

    private String validateString(String str) {
        if (str == null)
            return null;

        str = str.trim();
        if (str.isEmpty())
            return null;

        return str;
    }
}