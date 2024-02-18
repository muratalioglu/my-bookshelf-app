package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.MemberBookDTO;
import com.example.mybookshelfapp.dto.MemberBookInDTO;

public interface MemberBookService {

    MemberBookDTO getMemberBooks(Integer memberId);

    void addBookToMember(MemberBookInDTO dto, Integer memberId);

    void removeBookFromMember(Integer bookId, Integer memberId);

    void updateMemberBook(Integer memberBookId, String status, Integer currentPage);
}