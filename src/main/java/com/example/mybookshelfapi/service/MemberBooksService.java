package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBooksDTO;

public interface MemberBooksService {

    MemberBooksDTO getMemberBooks(Integer memberId);
}