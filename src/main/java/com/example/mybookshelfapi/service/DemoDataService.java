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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoDataService implements CommandLineRunner {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;

    @Override
    public void run(String... args) {

        InputStream booksJsonFile;
        List<Book> bookList;
        try {
            booksJsonFile = new ClassPathResource("static/books.json").getInputStream();
            bookList =
                    new Gson().fromJson(
                            new String(
                                    booksJsonFile.readAllBytes()
                            ),
                            new TypeToken<List<Book>>() {
                            }.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bookRepository.saveAll(bookList);

        InputStream memberBooksJsonFile;
        List<MemberBook> memberBookList;
        try {
            memberBooksJsonFile = new ClassPathResource("static/memberBooks.json").getInputStream();
            memberBookList =
                    new Gson().fromJson(
                            new String(
                                    memberBooksJsonFile.readAllBytes()
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
