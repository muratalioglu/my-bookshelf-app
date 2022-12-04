package com.example.mybookshelfapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column
    Integer memberId;

    @Column
    Integer bookId;

    @Column
    Timestamp createTime;

    @Column
    Boolean deleted = false;

    @Column
    Timestamp deleteTime;
}