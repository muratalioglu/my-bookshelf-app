package com.example.mybookshelfapi.service;

import com.example.mybookshelfapi.dto.AuthInDTO;
import com.example.mybookshelfapi.dto.RegisterInDTO;

public interface AuthService {

    Integer register(RegisterInDTO dto);

    String login(AuthInDTO dto);
}