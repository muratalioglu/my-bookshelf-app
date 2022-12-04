package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBooksDTO;
import com.example.mybookshelfapi.entity.Book;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.entity.MemberBooks;
import com.example.mybookshelfapi.repository.BookRepository;
import com.example.mybookshelfapi.repository.MemberBooksRepository;
import com.example.mybookshelfapi.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberBooksServiceImpl implements MemberBooksService {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBooksRepository memberBooksRepository;

    public MemberBooksServiceImpl(BookRepository bookRepository,
                                  MemberRepository memberRepository,
                                  MemberBooksRepository memberBooksRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.memberBooksRepository = memberBooksRepository;
    }

    @Override
    public MemberBooksDTO getMemberBooks(Integer memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Member cannot found with the given id!")
                );

        List<MemberBooks> memberBooksList = memberBooksRepository.findByMemberId(memberId);
        if (memberBooksList.isEmpty())
            return null;

        Set<Integer> bookIdSet =
                memberBooksList.stream()
                        .map(MemberBooks::getBookId)
                        .collect(Collectors.toSet());

        List<Book> bookList = bookRepository.findAllById(bookIdSet);

        return new MemberBooksDTO(
                new MemberBooksDTO.Member(
                        memberId,
                        String.format("%s %s", member.getFirstName(), member.getLastName())
                ),
                bookList.stream()
                        .map(
                                book -> new MemberBooksDTO.Book(
                                        book.getTitle(),
                                        book.getDescription(),
                                        book.getAuthor(),
                                        book.getIsbn(),
                                        book.getLanguage(),
                                        book.getPublicationYear(),
                                        book.getPages()
                                )
                        )
                        .collect(Collectors.toList())
        );
    @Override
    public Integer addBookToMember(MemberBooksInDTO dto, Integer memberId) {

        if (!bookRepository.existsById(dto.getBookId()))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book cannot found with the given id!"
            );

        if (!memberRepository.existsById(memberId))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member cannot found with the given id!"
            );

        if (memberBooksRepository.existsByBookIdAndMemberIdAndDeletedFalse(dto.getBookId(), memberId))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("The book with id [%d] is already on the member's shelf.", dto.getBookId())
            );

        MemberBooks memberBooks = new MemberBooks();
        memberBooks.setMemberId(memberId);
        memberBooks.setBookId(dto.getBookId());
        memberBooks.setCreateTime(new Timestamp(System.currentTimeMillis()));

        memberBooksRepository.save(memberBooks);

        return memberBooks.getId();
    }
}
