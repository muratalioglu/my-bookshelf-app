package com.example.mybookshelfapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberBookDTO {

    Integer memberId;

    List<Book> books;

    @Data
    @AllArgsConstructor
    public static class Book {
        Integer id;
        String name;
        String status;
        Integer currentPage;
    }
}