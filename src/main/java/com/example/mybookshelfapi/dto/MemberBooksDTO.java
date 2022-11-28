package com.example.mybookshelfapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberBooksDTO {

    Member member;
    List<Book> books;

    @Data
    @AllArgsConstructor
    public static class Member {
        Integer id;
        String name;
    }

    @Data
    @AllArgsConstructor
    public static class Book {
        String title;
        String description;
        String author;
        String isbn;
        String language;
        Integer publicationYear;
        Integer pages;
    }
}