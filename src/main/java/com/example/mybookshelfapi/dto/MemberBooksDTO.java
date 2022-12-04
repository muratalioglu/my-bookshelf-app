package com.example.mybookshelfapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MemberBooksDTO {

    Integer memberId;

    List<Integer> bookIds;
}