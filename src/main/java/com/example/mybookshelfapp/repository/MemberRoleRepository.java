package com.example.mybookshelfapp.repository;

import com.example.mybookshelfapp.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Integer> {
}