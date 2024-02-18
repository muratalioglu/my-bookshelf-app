package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}