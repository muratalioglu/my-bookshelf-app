package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Member;
import com.example.mybookshelfapp.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getMember(String email) {
        return memberRepository.findFirstByEmail(email).orElse(null);
    }
}