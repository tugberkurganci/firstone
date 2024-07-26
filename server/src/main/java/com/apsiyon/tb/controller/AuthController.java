package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.*;
import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.repositories.AppUserRepository;
import com.apsiyon.tb.services.AppUserService;
import com.apsiyon.tb.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AppUserService appUserService;


    @PostMapping("/api/v1/auth")
    ResponseEntity<AuthResponse> handleAuthentication(@Valid @RequestBody Credentials creds) {
        var authResponse = authService.authenticate(creds);
        var cookie = ResponseCookie.from("hoax-token", authResponse.getToken().getToken()).path("/").httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(authResponse);
    }

    @PostMapping("/api/v1/logout")
    ResponseEntity<GenericMessage> handleLogout(@RequestHeader(name="Authorization", required = false) String authorizationHeader, @CookieValue(name="hoax-token", required = false) String cookieValue){
        var tokenWithPrefix = authorizationHeader;
        if(cookieValue != null){
            tokenWithPrefix = "AnyPrefix " +cookieValue;
        }

        var cookie = ResponseCookie.from("hoax-token", "").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new GenericMessage("Logout success"));
    }
    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user){

        appUserService.save(user.toUser());
        return new GenericMessage("create.user.success.message");
    }

}
