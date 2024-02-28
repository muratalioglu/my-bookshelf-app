package com.example.mybookshelfapp.controller;

import com.example.mybookshelfapp.dto.AuthInDTO;
import com.example.mybookshelfapp.dto.BookInDTO;
import com.example.mybookshelfapp.entity.Member;
import com.example.mybookshelfapp.entity.MemberRole;
import com.example.mybookshelfapp.enums.RoleType;
import com.example.mybookshelfapp.repository.MemberRepository;
import com.example.mybookshelfapp.repository.MemberRoleRepository;
import com.example.mybookshelfapp.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
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

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    private final AuthService authService;

    private final MemberRoleRepository memberRoleRepository;
    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static String accessToken;

    @Autowired
    public BookControllerTest(AuthService authService,
                              MemberRoleRepository memberRoleRepository,
                              MemberRepository memberRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder,
                              MockMvc mockMvc) {

        this.authService = authService;
        this.memberRoleRepository = memberRoleRepository;
        this.memberRepository = memberRepository;
        this.mockMvc = mockMvc;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private String getAccessToken() {
        if (accessToken == null) {

            Member member = new Member();
            member.setEmail("admin@mybookshelf");
            member.setFirstName("Murat Deniz");
            member.setLastName("Alioglu");
            member.setRegistrationTime(Timestamp.valueOf(LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0)));
            member.setPassword(bCryptPasswordEncoder.encode("P4ssw0rd"));
            memberRepository.save(member);

            MemberRole memberRole = new MemberRole(member.getId(), RoleType.ADMIN.getValue());
            memberRoleRepository.save(memberRole);

            accessToken = "Bearer " + authService.login(new AuthInDTO("admin@mybookshelf", "P4ssw0rd"));
        }
        return accessToken;
    }

    @Test
    public void testGetBook() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/books/" + 1)
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        getAccessToken()
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/books/" + Integer.MAX_VALUE)
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        getAccessToken()
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
                                getAccessToken()
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
                                        getAccessToken()
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteBook() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/books/" + 1)
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        getAccessToken()
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}