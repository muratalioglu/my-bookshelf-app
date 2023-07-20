package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBookDTO;
import com.example.mybookshelfapi.dto.MemberBookInDTO;

public interface MemberBookService {

    MemberBookDTO getMemberBooks(Integer memberId);

    void addBookToMember(MemberBookInDTO dto, Integer memberId);

    void removeBookFromMember(Integer bookId, Integer memberId);

    void updateMemberBook(Integer memberBookId, String status, Integer currentPage);
}