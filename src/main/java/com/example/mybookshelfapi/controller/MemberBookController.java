package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBookDTO;
import com.example.mybookshelfapi.dto.MemberBookInDTO;
import com.example.mybookshelfapi.service.MemberBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.Collection;

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

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> addBookToMember(Principal principal,
                                                @RequestBody MemberBookInDTO dto,
                                                @PathVariable @Positive Integer memberId) {

        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) principal;
        Collection<GrantedAuthority> authorities= jwtToken.getAuthorities();

        if (authorities.stream().noneMatch(authority -> authority.getAuthority().endsWith("editor")))
            memberId = Integer.parseInt(principal.getName());

        memberBookService.addBookToMember(dto, memberId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeBookFromMember(Principal principal,
                                                     @PathVariable @Positive Integer memberId,
                                                     @RequestParam @Positive Integer bookId) {

        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) principal;
        Collection<GrantedAuthority> authorities= jwtToken.getAuthorities();

        if (authorities.stream().noneMatch(authority -> authority.getAuthority().endsWith("editor")))
            memberId = Integer.parseInt(principal.getName());

        memberBookService.removeBookFromMember(bookId, memberId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}