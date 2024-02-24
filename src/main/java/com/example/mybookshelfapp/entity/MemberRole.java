package com.example.mybookshelfapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "member_id")
    Integer memberId;

    @Column(name = "role_id")
    Integer roleId;

    public MemberRole(Integer memberId, Integer roleId) {
        this.memberId = memberId;
        this.roleId = roleId;
    }
}