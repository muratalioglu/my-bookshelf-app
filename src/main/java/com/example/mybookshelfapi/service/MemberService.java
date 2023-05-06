package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Member;

public interface MemberService {

    Member getMember(String email);
}