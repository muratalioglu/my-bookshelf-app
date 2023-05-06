package com.example.mybookshelfapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    String email;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    Timestamp registrationTime;

    @Column
    String password;

    @OneToMany
    @JoinColumn(name = "member_id")
    Set<Authority> authorities;
}
