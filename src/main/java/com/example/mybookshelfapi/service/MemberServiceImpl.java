package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.repository.MemberRepository;
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