package com.apsiyon.tb.services;

import com.apsiyon.tb.entities.*;
import com.apsiyon.tb.repositories.AppUserRepository;
import com.apsiyon.tb.repositories.EmployeeRepository;
import com.apsiyon.tb.repositories.ManagerRepository;
import com.apsiyon.tb.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser inDB = appUserRepository.findByEmail(email);
        if(inDB == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }
        return inDB;
    }
    @Transactional()
    public void save(AppUser user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            if( user.getRole().toString().equals(Role.USER.toString())){
                User user1=new User();
                user1.setPassword(user.getPassword());
                user1.setEmail(user.getEmail());
                user1.setRole(Role.USER);
            userRepository.saveAndFlush(user1);} else if
            (user.getRole().toString().equals(Role.MANAGER.toString())) {
                Manager user1=new Manager();
                user1.setPassword(user.getPassword());
                user1.setEmail(user.getEmail());
                user1.setRole(Role.MANAGER);
                managerRepository.saveAndFlush(user1);
            }
            else if (user.getRole().toString().equals(Role.EMPLOYEE.toString())) {
                Employee user1=new Employee();
                user1.setPassword(user.getPassword());
                user1.setEmail(user.getEmail());
                user1.setRole(Role.EMPLOYEE);
            employeeRepository.save(user1);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }}