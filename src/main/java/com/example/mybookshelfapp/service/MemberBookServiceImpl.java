package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.MemberBookDTO;
import com.example.mybookshelfapp.dto.MemberBookInDTO;
import com.example.mybookshelfapp.entity.Book;
import com.example.mybookshelfapp.entity.MemberBook;
import com.example.mybookshelfapp.enums.MemberBookStatus;
import com.example.mybookshelfapp.repository.BookRepository;
import com.example.mybookshelfapp.repository.MemberBookRepository;
import com.example.mybookshelfapp.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MemberBookServiceImpl implements MemberBookService {

    final BookRepository bookRepository;
    final MemberRepository memberRepository;
    final MemberBookRepository memberBookRepository;

    public MemberBookServiceImpl(BookRepository bookRepository,
                                 MemberRepository memberRepository,
                                 MemberBookRepository memberBookRepository) {

        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.memberBookRepository = memberBookRepository;
    }

    @Override
    public List<MemberBookDTO> getMemberBooks(Integer memberId) {

        validateMemberExistence(memberId);

        List<MemberBook> memberBookList = memberBookRepository.findByMemberIdAndDeletedFalse(memberId);
        if (memberBookList.isEmpty())
            return null;

        Set<Integer> bookIdSet = memberBookList.stream()
                .map(MemberBook::getBookId)
                .collect(Collectors.toSet());

        Map<Integer, Book> bookMap =
                bookRepository.findByIdInAndDeleteTimeIsNull(bookIdSet).stream()
                        .collect(
                                Collectors.toMap(
                                        Book::getId,
                                        Function.identity()
                                )
                        );

        return memberBookList.stream()
                .map(memberBook ->
                        new MemberBookDTO(
                                memberBook.getBookId(),
                                bookMap.get(memberBook.getBookId()).getTitle(),
                                memberBook.getStatus(),
                                memberBook.getCurrentPage()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public void addBookToMember(MemberBookInDTO dto, Integer memberId) {

        validateMemberExistence(memberId);

        if (!bookRepository.existsById(dto.getBookId()))
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book cannot found with the given id!"
            );

        if (memberBookRepository.existsByBookIdAndMemberIdAndDeletedFalse(dto.getBookId(), memberId))
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    String.format("The book with id [%d] is already on the member's shelf.", dto.getBookId())
            );

        MemberBook memberBook = new MemberBook();
        memberBook.setMemberId(memberId);
        memberBook.setBookId(dto.getBookId());

        memberBookRepository.save(memberBook);
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

        Optional<MemberBook> memberBookOptional = memberBookRepository.findByMemberIdAndBookIdAndDeletedFalse(memberId, bookId);

        memberBookOptional.ifPresentOrElse(
                (memberBook) -> {
                    memberBook.setDeleted(true);
                    memberBook.setDeleteTime(new Timestamp(System.currentTimeMillis()));
                    memberBookRepository.save(memberBook);
                },
                () -> {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Member Book cannot found!"
                    );
                }
        );
    }

    @Override
    public void updateMemberBook(Integer memberId, Integer bookId, String status, Integer currentPage) {

        if (status == null && currentPage == null)
            return;

        validateMemberExistence(memberId);

        Optional<MemberBook> memberBookOptional = memberBookRepository.findByMemberIdAndBookId(memberId, bookId);
        if (memberBookOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Member Book cannot found!"
            );

        MemberBook memberBook = memberBookOptional.get();
        boolean updated = false;

        if (status != null && !status.isEmpty()) {

            if (!MemberBookStatus.isValid(status))
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid member book status!"
                );

            if (memberBook.getStatus() == null || !memberBook.getStatus().equals(status)) {
                memberBook.setStatus(status);
                updated = true;
            }
        }

        if (currentPage != null) {
            if (memberBook.getCurrentPage() == null || !memberBook.getCurrentPage().equals(currentPage)) {
                memberBook.setCurrentPage(currentPage);
                updated = true;
            }
        }

        if (updated)
            memberBookRepository.save(memberBook);
    }
}