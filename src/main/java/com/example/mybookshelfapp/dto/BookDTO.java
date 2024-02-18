package com.example.mybookshelfapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {

    Integer id;

    String title;

    String description;

    String author;

    String isbn;

    String language;

    Integer publicationYear;

    Integer pages;
}
