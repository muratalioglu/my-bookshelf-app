package com.example.mybookshelfapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Integer id;

    @Column(name = "member_id")
    Integer memberId;

    @Column
    String role;

    public Role(Integer memberId, String role) {
        this.memberId = memberId;
        this.role = role;
    }
}