package com.example.mybookshelfapp;

import com.example.mybookshelfapp.controller.AuthController;
import com.example.mybookshelfapp.controller.BookController;
import com.example.mybookshelfapp.controller.MemberBookController;
import com.example.mybookshelfapp.service.AuthService;
import com.example.mybookshelfapp.service.BookService;
import com.example.mybookshelfapp.service.MemberBookService;
import com.example.mybookshelfapp.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MyBookshelfAppApplicationTests {

	@Autowired private AuthController authController;
	@Autowired private BookController bookController;
	@Autowired private MemberBookController memberBookController;
	@Autowired private AuthService authService;
	@Autowired private BookService bookService;
	@Autowired private MemberService memberService;
	@Autowired private MemberBookService memberBookService;

	@Test
	void contextLoads() {
		assertNotNull(authController);
		assertNotNull(bookController);
		assertNotNull(memberBookController);

		assertNotNull(authService);
		assertNotNull(bookService);
		assertNotNull(memberService);
		assertNotNull(memberBookService);
	}
}
