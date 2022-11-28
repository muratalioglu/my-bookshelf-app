package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member-books")
public class MemberBooksController {

    final MemberBooksService memberBooksService;

    public MemberBooksController(MemberBooksService memberBooksService) {
        this.memberBooksService = memberBooksService;
    }

    @GetMapping
    public ResponseEntity<List<MemberBookDTO>> getMemberBooks() {

        List<MemberBookDTO> memberBookDTOList = memberBooksService.getMemberBooks();
        if (memberBookDTOList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(memberBookDTOList);
    }
}