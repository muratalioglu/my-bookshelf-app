package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Book;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.entity.MemberBooks;
import com.example.mybookshelfapi.repository.BookRepository;
import com.example.mybookshelfapi.repository.MemberBooksRepository;
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
    final MemberBooksRepository memberBooksRepository;

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
                            new TypeToken<List<Book>>() {}.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bookRepository.saveAll(bookList);

        File membersJsonFile;
        List<Member> memberList;
        try {
            membersJsonFile = new ClassPathResource("static/members.json").getFile();
            memberList =
                    new Gson().fromJson(
                            new String(
                                    Files.readAllBytes(membersJsonFile.toPath())
                            ),
                            new TypeToken<List<Member>>() {}.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        memberRepository.saveAll(memberList);

        File memberBooksJsonFile;
        List<MemberBooks> memberBooksList;
        try {
            memberBooksJsonFile = new ClassPathResource("static/memberBooks.json").getFile();
            memberBooksList =
                    new Gson().fromJson(
                            new String(
                                    Files.readAllBytes(
                                            memberBooksJsonFile.toPath()
                                    )
                            ),
                            new TypeToken<List<MemberBooks>>() {}.getType()
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        memberBooksRepository.saveAll(memberBooksList);
    }
}
