package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Member;

public interface MemberService  {

    Member getMember(String email);
}