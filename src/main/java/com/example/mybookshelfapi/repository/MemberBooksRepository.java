package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.MemberBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberBooksRepository extends JpaRepository<MemberBooks, Integer> {

    List<MemberBooks> findByMemberId(Integer memberId);
}