package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.dto.AuthInDTO;
import com.example.mybookshelfapi.dto.RegisterInDTO;
import com.example.mybookshelfapi.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthInDTO dto) {
        String accessToken = authService.login(dto);
        return ResponseEntity.ok(accessToken);
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody @Valid RegisterInDTO dto) {
        Integer memberId = authService.register(dto);
        return ResponseEntity.ok()
                .header("memberId", memberId.toString())
                .build();
    }
}