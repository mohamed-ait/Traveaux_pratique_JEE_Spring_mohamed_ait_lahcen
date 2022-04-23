package com.example.students_management.security.service;

import com.example.students_management.security.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityService securityService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=securityService.loadUserByUserName(username);
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(role->{
            SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRoleName());
            authorities.add(authority);
        });
        //second method to create collections of User roles using stream :
        Collection<GrantedAuthority> authorities1=appUser.getRoles().stream()
                .map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName()))
                .collect(Collectors.toList());
        User springUser=new User(appUser.getUserName(), appUser.getPassword(),authorities);
        return springUser;
    }
}
