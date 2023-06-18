package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Member;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

    Member getMember(String email);
}