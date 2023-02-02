package com.example.mybookshelfapi.controller;

import com.example.mybookshelfapi.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> auth(Authentication authentication) {
        log.debug("Token requested for user: '{}'", authentication.getName());
        String accessToken = tokenService.generateToken(authentication);
        return ResponseEntity.ok(accessToken);
    }
}