package com.example.mybookshelfapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberBookDTO {
    Integer memberId;
    Integer bookId;
}