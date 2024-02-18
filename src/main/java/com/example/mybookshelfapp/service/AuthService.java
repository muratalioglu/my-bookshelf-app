package com.example.mybookshelfapp.service;

import com.example.mybookshelfapp.dto.AuthInDTO;
import com.example.mybookshelfapp.dto.RegisterInDTO;

public interface AuthService {

    Integer register(RegisterInDTO dto);

    String login(AuthInDTO dto);
}