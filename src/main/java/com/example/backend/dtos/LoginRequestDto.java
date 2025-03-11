package com.example.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequestDto {
    @JsonProperty("username")
    public String username;

    @JsonProperty("password")
    public String password;
}
