package com.apsiyon.tb.dto;


import com.apsiyon.tb.entities.Token;

public class AuthResponse {

    UserDTO user;

    Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}