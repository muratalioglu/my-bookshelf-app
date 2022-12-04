package com.example.mybookshelfapi.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class MemberBookInDTO {

    @Positive
    Integer bookId;
}