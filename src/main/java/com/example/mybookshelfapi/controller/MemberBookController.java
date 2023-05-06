package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBookDTO;
import com.example.mybookshelfapi.dto.MemberBookInDTO;
import com.example.mybookshelfapi.service.MemberBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping("/member-books")
public class MemberBookController {

    final MemberBookService memberBookService;

    public MemberBookController(MemberBookService memberBookService) {
        this.memberBookService = memberBookService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberBookDTO> getMemberBooks(@PathVariable @Positive Integer memberId) {

        MemberBookDTO memberBookDTO = memberBookService.getMemberBooks(memberId);
        if (memberBookDTO == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberBookDTO);
    }

    @PostMapping
    public ResponseEntity<Void> addBookToMember(Principal principal,
                                                @RequestBody MemberBookInDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        memberBookService.addBookToMember(dto, memberId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeBookFromMember(@PathVariable @Positive Integer memberId,
                                                     @RequestParam @Positive Integer bookId) {

        memberBookService.removeBookFromMember(bookId, memberId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}