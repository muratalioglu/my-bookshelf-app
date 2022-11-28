package com.example.mybookshelfapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class BookInDTO {

    @NotBlank(message = "Title is required")
    String title;

    String description;

    String author;

    String isbn;
    
    String language;

    @Positive
    Integer publicationYear;

    @Positive
    Integer pages;
}