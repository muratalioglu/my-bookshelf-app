package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBooksDTO;
import com.example.mybookshelfapi.dto.MemberBooksInDTO;

public interface MemberBooksService {

    MemberBooksDTO getMemberBooks(Integer memberId);

    Integer addBookToMember(MemberBooksInDTO dto, Integer memberId);
}