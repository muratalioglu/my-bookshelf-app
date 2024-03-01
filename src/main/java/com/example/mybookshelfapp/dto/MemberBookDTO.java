package com.example.mybookshelfapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberBookDTO {
    Integer id;
    String name;
    String status;
    Integer currentPage;
}