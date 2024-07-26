package com.apsiyon.tb.services;

import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.entities.Token;
import com.apsiyon.tb.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.apsiyon.tb.dto.Credentials;
import java.util.Base64;

@Service
public class TokenService {

    @Autowired
    AppUserRepository userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Token createToken(AppUser user, Credentials creds) {
        String emailColonPassword = creds.email() + ":" + creds.password();
        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic", token);
    }


    public AppUser verifyToken(String authorizationHeader) {
        if (authorizationHeader == null) return null;

        var base64Encoded = authorizationHeader.split(" ")[1];
        var decoded = new String(Base64.getDecoder().decode(base64Encoded));
        var credentials = decoded.split(":");
        var email = credentials[0];
        var password = credentials[1];
        AppUser inDB = userService.findByEmail(email);
        if (inDB == null) return null;
        if (!passwordEncoder.matches(password, inDB.getPassword())) return null;
        return inDB;
    }
}