package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.MemberBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBooksRepository extends JpaRepository<MemberBooks, Integer> {
}