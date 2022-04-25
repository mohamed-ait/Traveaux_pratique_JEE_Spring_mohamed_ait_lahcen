package com.example.students_management.security.service;

import com.example.students_management.security.entities.AppRole;
import com.example.students_management.security.entities.AppUser;
import com.example.students_management.security.repositories.AppRoleRepository;
import com.example.students_management.security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Slf4j
@Transactional
@NoArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("password no match");
        String hashedPassword=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        //appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUserName(username);
        appUser.setPassword(hashedPassword);
        appUser.setActive(true);
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("role "+roleName+"already exist");
        appRole=new AppRole();
        appRole.setRoleName(roleName);
        appRole.setDescription(description);
        AppRole savedRole=appRoleRepository.save(appRole);
        return savedRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        AppUser appUser=appUserRepository.findByUserName(username);
        if(appUser==null) throw new RuntimeException("user not found");
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        AppUser appUser=appUserRepository.findByUserName(username);
        if(appUser==null) throw new RuntimeException("user not found");
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
       // if(appUserRepository.findByUserName(username)==null) throw new RuntimeException("user no found!");
        return appUserRepository.findByUserName(username);
    }
}