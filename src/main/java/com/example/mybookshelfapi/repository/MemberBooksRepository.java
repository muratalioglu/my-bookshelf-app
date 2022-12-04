package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.MemberBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBooksRepository extends JpaRepository<MemberBooks, Integer> {

    List<MemberBooks> findByMemberId(Integer memberId);

    boolean existsByBookIdAndMemberIdAndDeletedFalse(Integer bookId, Integer memberId);

    Optional<MemberBooks> findByMemberIdAndBookIdAndDeletedFalse(Integer memberId, Integer bookId);
}