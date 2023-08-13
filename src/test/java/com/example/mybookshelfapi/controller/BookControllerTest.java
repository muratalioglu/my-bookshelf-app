package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.AuthInDTO;
import com.example.mybookshelfapi.dto.BookInDTO;
import com.example.mybookshelfapi.entity.Authority;
import com.example.mybookshelfapi.entity.Member;
import com.example.mybookshelfapi.repository.MemberRepository;
import com.example.mybookshelfapi.service.AuthService;
import com.example.mybookshelfapi.service.AuthorityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private final BookController bookController;

    private final AuthService authService;

    private final AuthorityRepository authorityRepository;
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BookControllerTest(BookController bookController,
                              AuthService authService,
                              AuthorityRepository authorityRepository,
                              MemberRepository memberRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              MockMvc mockMvc ) {

        this.bookController = bookController;
        this.authService = authService;
        this.authorityRepository = authorityRepository;
        this.memberRepository = memberRepository;
        this.mockMvc = mockMvc;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @BeforeEach
    public void saveMockUser() {
        Member member = new Member();
        member.setEmail("admin@mybookshelf");
        member.setFirstName("Murat Deniz");
        member.setLastName("Alioglu");
        member.setRegistrationTime(Timestamp.valueOf(LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)));
        member.setPassword(bCryptPasswordEncoder.encode("P4ssw0rd"));
        memberRepository.save(member);

        authorityRepository.saveAll(
                Set.of(
                        new Authority(member.getId(), "user"),
                        new Authority(member.getId(), "editor")
                )
        );
    }

    @Test
    public void testGetBook() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/books/" + 1)
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        "Bearer " + authService.login(new AuthInDTO("admin@mybookshelf", "P4ssw0rd")
                                        )
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/books/" + Integer.MAX_VALUE)
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        "Bearer " + authService.login(new AuthInDTO("admin@mybookshelf", "P4ssw0rd")
                                        )
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateBook() throws Exception {

        BookInDTO bookInDTO = new BookInDTO();
        bookInDTO.setTitle("Gölgesizler");
        bookInDTO.setDescription(null);
        bookInDTO.setAuthor("Hasan Ali Toptaş");
        bookInDTO.setPublicationYear(null);
        bookInDTO.setIsbn("9786051850672");
        bookInDTO.setLanguage("tr");
        bookInDTO.setPages(240);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/books")
                        .content(objectMapper.writeValueAsString(bookInDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header(
                                HttpHeaders.AUTHORIZATION,
                                "Bearer " + authService.login(new AuthInDTO("admin@mybookshelf", "P4ssw0rd"))
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("BookId"));
    }

    @Test
    public void testUpdateBook() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .patch("/books/1?description=TestAmacliDescription")
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        "Bearer " + authService.login(new AuthInDTO("admin@mybookshelf", "P4ssw0rd"))
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}