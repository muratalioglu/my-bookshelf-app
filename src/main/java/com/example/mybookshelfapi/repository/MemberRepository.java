package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findFirstByEmail(String email);
}