package com.example.springsecurity.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        String role = "adminsss";
        List<SimpleGrantedAuthority> authList = getAuthorities(role);
        System.out.println("USer Role = " + authList);
//        return new User("admin123" , "admin", authList); // only bye
        return new User("john123" , "password", authList); // only hello

    }
    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
    //    authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        if (role != null && role.trim().length() > 0) {
            if (role.equals("admin")) {
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            else {
                authList.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }

        return authList;
    }

}