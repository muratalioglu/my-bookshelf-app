package com.example.mybookshelfapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Integer id;

    @Column(name = "member_id")
    Integer memberId;

    @Column
    String authority;
}