package com.apsiyon.tb.dto;

import com.apsiyon.tb.entities.AppUser;
import com.apsiyon.tb.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
public record UserCreate(

        @NotBlank
        @Email
        String email,

        @Size(min = 8, max=255)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hoaxify.constraint.password.pattern}")
        String password

        ,String role
) {

    public AppUser toUser(){
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPassword(password);
        if(role.equals("m")){user.setRole(Role.MANAGER);
        } else if (role.equals("e")) {user.setRole(Role.EMPLOYEE);

        }else user.setRole(Role.USER);
        return user;
    }

}