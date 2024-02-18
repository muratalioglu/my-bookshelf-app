package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    Member getMember(String email);
}