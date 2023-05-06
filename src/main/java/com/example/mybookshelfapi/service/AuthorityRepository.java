package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}