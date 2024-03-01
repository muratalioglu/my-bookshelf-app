package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.MemberBookDTO;
import com.example.mybookshelfapp.dto.MemberBookInDTO;

import java.util.List;

public interface MemberBookService {

    List<MemberBookDTO> getMemberBooks(Integer memberId);

    void addBookToMember(MemberBookInDTO dto, Integer memberId);

    void removeBookFromMember(Integer bookId, Integer memberId);

    void updateMemberBook(Integer memberId, Integer bookId, String status, Integer currentPage);
}