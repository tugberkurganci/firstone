package com.apsiyon.tb.dto;


import com.apsiyon.tb.entities.AppUser;

public class UserDTO {

    long id;



    String email;



    String role;



    public UserDTO(AppUser user){
        setId(user.getId());

        setEmail(user.getEmail());

        setRole(user.getRole().toString());

    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}