package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Book;
import com.example.mybookshelfapp.entity.Member;
import com.example.mybookshelfapp.entity.MemberBook;
import com.example.mybookshelfapp.entity.MemberRole;
import com.example.mybookshelfapp.enums.RoleType;
import com.example.mybookshelfapp.repository.BookRepository;
import com.example.mybookshelfapp.repository.MemberBookRepository;
import com.example.mybookshelfapp.repository.MemberRepository;
import com.example.mybookshelfapp.repository.MemberRoleRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoDataService implements CommandLineRunner {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;
    final MemberRoleRepository memberRoleRepository;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

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

        Member member = new Member();
        member.setEmail("admin@mybookshelf");
        member.setFirstName("Murat Deniz");
        member.setLastName("Alioglu");
        member.setRegistrationTime(Timestamp.valueOf(LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)));
        member.setPassword(bCryptPasswordEncoder.encode("P4ssw0rd"));
        memberRepository.save(member);

        memberRoleRepository.save(new MemberRole(member.getId(), RoleType.ADMIN.getValue()));
    }
}