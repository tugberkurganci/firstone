package com.apsiyon.tb.services;

import com.apsiyon.tb.dto.AuthResponse;
import com.apsiyon.tb.dto.AuthenticationException;
import com.apsiyon.tb.dto.Credentials;
import com.apsiyon.tb.dto.UserDTO;
import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.entities.Token;
import com.apsiyon.tb.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AppUserRepository userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        AppUser inDB = userService.findByEmail(creds.email());
        if(inDB == null) throw new AuthenticationException();
        if(!passwordEncoder.matches(creds.password(), inDB.getPassword())) throw new AuthenticationException();
        Token token = tokenService.createToken(inDB, creds);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;

    }


}
