package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBooksDTO;
import com.example.mybookshelfapi.dto.MemberBooksInDTO;
import com.example.mybookshelfapi.service.MemberBooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/member-books")
public class MemberBooksController {

    final MemberBooksService memberBooksService;

    public MemberBooksController(MemberBooksService memberBooksService) {
        this.memberBooksService = memberBooksService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberBooksDTO> getMemberBooks(@PathVariable @Positive Integer memberId) {

        MemberBooksDTO memberBooksDTO = memberBooksService.getMemberBooks(memberId);
        if (memberBooksDTO == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(memberBooksDTO);
    }

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> addBookToMember(@PathVariable @Positive Integer memberId,
                                                @RequestBody MemberBooksInDTO dto) {

        memberBooksService.addBookToMember(dto, memberId);

        return ResponseEntity.noContent().build();
    }
}