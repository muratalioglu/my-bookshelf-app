package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Book;
import com.example.mybookshelfapi.entity.MemberBook;
import com.example.mybookshelfapi.repository.BookRepository;
import com.example.mybookshelfapi.repository.MemberBookRepository;
import com.example.mybookshelfapi.repository.MemberRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoDataService implements CommandLineRunner {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;

    @Override
    public void run(String... args) {

        File booksJsonFile;
        List<Book> bookList;
        try {
            booksJsonFile = new ClassPathResource("static/books.json").getFile();
            bookList =
                    new Gson().fromJson(
                            new String(
                                    Files.readAllBytes(booksJsonFile.toPath())
                            ),
                            new TypeToken<List<Book>>() {
                            }.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bookRepository.saveAll(bookList);

        File memberBooksJsonFile;
        List<MemberBook> memberBookList;
        try {
            memberBooksJsonFile = new ClassPathResource("static/memberBooks.json").getFile();
            memberBookList =
                    new Gson().fromJson(
                            new String(
                                    Files.readAllBytes(
                                            memberBooksJsonFile.toPath()
                                    )
                            ),
                            new TypeToken<List<MemberBook>>() {
                            }.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        memberBookRepository.saveAll(memberBookList);
    }
}
