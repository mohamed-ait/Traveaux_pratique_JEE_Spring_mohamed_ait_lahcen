package ma.enset.pateintsmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.pateintsmvc.security.entities.Role;
import ma.enset.pateintsmvc.security.entities.User;
import ma.enset.pateintsmvc.security.repositories.AppRoleRepository;
import ma.enset.pateintsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@NoArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public SecurityServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("password no match");
        String hashedPassword=passwordEncoder.encode(password);
        User appUser=new User();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUserName(username);
        appUser.setPassword(hashedPassword);
        appUser.setActive(true);
        User savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public Role saveNewRole(String roleName, String description) {
       Role appRole=appRoleRepository.findByRoleName(roleName);
       if(appRole!=null) throw new RuntimeException("role "+roleName+"already exist");
       appRole=new Role();
       appRole.setRoleName(roleName);
       appRole.setDescription(description);
       Role savedRole=appRoleRepository.save(appRole);
       return savedRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
     Role appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
     User appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("user not found");
     appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        Role appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        User appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("user not found");
        appUser.getRoles().remove(appRole);
    }

    @Override
    public User loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
