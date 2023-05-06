package com.example.mybookshelfapi.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterInDTO {

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;
}