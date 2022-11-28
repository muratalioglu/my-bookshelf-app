package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBookDTO;

import java.util.List;

public interface MemberBooksService {

    List<MemberBookDTO> getMemberBooks();
}