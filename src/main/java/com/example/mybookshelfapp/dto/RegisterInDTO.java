package com.example.mybookshelfapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterInDTO {

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;
}