package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.MemberBooksDTO;
import com.example.mybookshelfapi.dto.MemberBooksInDTO;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.entity.MemberBooks;
import com.example.mybookshelfapi.repository.BookRepository;
import com.example.mybookshelfapi.repository.MemberBooksRepository;
import com.example.mybookshelfapi.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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

        validateMemberExistence(memberId);

        List<MemberBooks> memberBooksList = memberBooksRepository.findByMemberId(memberId);
        if (memberBooksList.isEmpty())
            return null;

        List<Integer> bookIdList =
                memberBooksList.stream()
                        .map(MemberBooks::getBookId)
                        .collect(Collectors.toList());

        return new MemberBooksDTO(memberId, bookIdList);
    }

    @Override
    public void addBookToMember(MemberBooksInDTO dto, Integer memberId) {

        if (!bookRepository.existsById(dto.getBookId()))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book cannot found with the given id!"
            );

        validateMemberExistence(memberId);

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
    }

    private void validateMemberExistence(Integer memberId) {
        if (!memberRepository.existsById(memberId))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member cannot found with the given id!"
            );
    }

    @Override
    public void removeBookFromMember(Integer bookId, Integer memberId) {

        validateMemberExistence(memberId);

        Optional<MemberBooks> memberBooksOptional = memberBooksRepository.findByMemberIdAndBookIdAndDeletedFalse(memberId, bookId);

        memberBooksOptional.ifPresentOrElse(
                (memberBooks) -> {
                    memberBooks.setDeleted(true);
                    memberBooks.setDeleteTime(new Timestamp(System.currentTimeMillis()));
                    memberBooksRepository.save(memberBooks);
                },
                () -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Member Book cannot fount!"
                    );
                }
        );
    }
}
