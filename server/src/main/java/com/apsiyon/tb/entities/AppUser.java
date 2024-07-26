package com.apsiyon.tb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "app_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_gen")
    @TableGenerator(name = "user_table_gen", table = "id_generator", pkColumnName = "gen_name", valueColumnName = "gen_value", pkColumnValue = "user_id", initialValue = 0, allocationSize = 1)
    private Long id;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Request> requests;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

