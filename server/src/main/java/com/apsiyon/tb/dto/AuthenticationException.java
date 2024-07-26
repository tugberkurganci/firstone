package com.apsiyon.tb.dto;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(){
        super("auth.invalid.credentials");
    }

}