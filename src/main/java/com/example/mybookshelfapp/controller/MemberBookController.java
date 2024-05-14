package com.example.mybookshelfapp.controller;

import com.example.mybookshelfapp.dto.MemberBookDTO;
import com.example.mybookshelfapp.dto.MemberBookInDTO;
import com.example.mybookshelfapp.service.MemberBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/members")
@Validated
public class MemberBookController {

    final MemberBookService memberBookService;

    public MemberBookController(MemberBookService memberBookService) {
        this.memberBookService = memberBookService;
    }

    @GetMapping("/{memberId}/books")
    public ResponseEntity<List<MemberBookDTO>> getMemberBooks(@PathVariable @Positive Integer memberId) {

        List<MemberBookDTO> memberBookDtoList = memberBookService.getMemberBooks(memberId);
        if (memberBookDtoList == null || memberBookDtoList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberBookDtoList);
    }

    @PostMapping("/{memberId}/books")
    public ResponseEntity<Void> addBookToMember(Principal principal,
                                                @RequestBody MemberBookInDTO dto,
                                                @PathVariable @Positive Integer memberId) {

        memberBookService.addBookToMember(dto, memberId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PatchMapping("/{memberId}/books/{bookId}")
    public ResponseEntity<Void> updateMemberBook(Principal principal,
                                                 @PathVariable @Positive Integer memberId,
                                                 @PathVariable @Positive Integer bookId,
                                                 @RequestParam(required = false) @Size(max = 20) String status,
                                                 @RequestParam(required = false) @Min(0) @Positive Integer currentPage) {

        memberBookService.updateMemberBook(
                memberId,
                bookId,
                status,
                currentPage
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{memberId}/books/{bookId}")
    public ResponseEntity<Void> removeBookFromMember(Principal principal,
                                                     @PathVariable @Positive Integer memberId,
                                                     @PathVariable @Positive Integer bookId) {

        memberBookService.removeBookFromMember(bookId, memberId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}