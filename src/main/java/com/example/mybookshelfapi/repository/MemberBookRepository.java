package com.example.mybookshelfapi.repository;

import com.example.mybookshelfapi.entity.MemberBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBookRepository extends JpaRepository<MemberBook, Integer> {

    List<MemberBook> findByMemberIdAndDeletedFalse(Integer memberId);

    boolean existsByBookIdAndMemberIdAndDeletedFalse(Integer bookId, Integer memberId);

    Optional<MemberBook> findByMemberIdAndBookIdAndDeletedFalse(Integer memberId, Integer bookId);
}