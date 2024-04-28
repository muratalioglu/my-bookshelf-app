package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.repository.BookRepository;
import com.example.mybookshelfapp.repository.MemberBookRepository;
import com.example.mybookshelfapp.repository.MemberRepository;
import com.example.mybookshelfapp.repository.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@RequiredArgsConstructor
public class DemoDataService implements ApplicationRunner {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;
    final MemberRoleRepository memberRoleRepository;

    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final DataSource dataSource;

    // todo: add conditional run
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.setScripts(new ClassPathResource("db_init.sql"));
        populator.execute(dataSource);
    }
}