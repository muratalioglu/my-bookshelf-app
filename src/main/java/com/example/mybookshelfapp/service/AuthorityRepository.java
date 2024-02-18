package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}