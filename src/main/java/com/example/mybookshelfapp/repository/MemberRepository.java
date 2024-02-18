package com.example.mybookshelfapp.repository;

import com.example.mybookshelfapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findFirstByEmail(String email);
}