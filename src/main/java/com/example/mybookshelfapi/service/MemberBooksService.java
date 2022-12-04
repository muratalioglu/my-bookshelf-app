package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBooksDTO;
import com.example.mybookshelfapi.dto.MemberBooksInDTO;

public interface MemberBooksService {

    MemberBooksDTO getMemberBooks(Integer memberId);

    void addBookToMember(MemberBooksInDTO dto, Integer memberId);

    void removeBookFromMember(Integer bookId, Integer memberId);
}