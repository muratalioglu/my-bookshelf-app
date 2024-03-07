package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.repository.BookRepository;
import com.example.mybookshelfapp.repository.MemberBookRepository;
import com.example.mybookshelfapp.repository.MemberRepository;
import com.example.mybookshelfapp.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class DemoDataService {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;
    final MemberRoleRepository memberRoleRepository;

    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final DataSource dataSource;

    @PostConstruct
    public void init() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setScripts(new ClassPathResource("db_init.sql"));
        populator.execute(dataSource);
    }
}