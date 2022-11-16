package com.example.mybookshelfapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class BookDTO {

    String title;

    String description;

    String author;

    Timestamp publicationDate;

    String isbn;

    String language;

    Integer pages;
}
