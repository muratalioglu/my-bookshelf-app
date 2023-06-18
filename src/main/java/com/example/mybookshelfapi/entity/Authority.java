package com.example.mybookshelfapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Integer id;

    @Column(name = "member_id")
    Integer memberId;

    @Column
    String authority;

    public Authority(Integer memberId, String authority) {
        this.memberId = memberId;
        this.authority = authority;
    }
}