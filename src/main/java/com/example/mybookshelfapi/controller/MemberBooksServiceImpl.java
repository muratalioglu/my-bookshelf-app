package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.MemberBookDTO;
import com.example.mybookshelfapi.repository.MemberBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberBooksServiceImpl implements MemberBooksService {

    final MemberBookRepository memberBookRepository;

    public MemberBooksServiceImpl(MemberBookRepository memberBookRepository) {
        this.memberBookRepository = memberBookRepository;
    }

    @Override
    public List<MemberBookDTO> getMemberBooks() {
        return memberBookRepository.findAll().stream()
                .map(
                        mb -> new MemberBookDTO(
                                mb.getMemberId(),
                                mb.getBookId()
                        )
                )
                .collect(Collectors.toList());
    }
}
