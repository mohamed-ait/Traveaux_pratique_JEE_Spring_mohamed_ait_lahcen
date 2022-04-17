package ma.enset.pateintsmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.pateintsmvc.security.entities.AppRole;
import ma.enset.pateintsmvc.security.entities.AppUser;
import ma.enset.pateintsmvc.security.repositories.AppRoleRepository;
import ma.enset.pateintsmvc.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        if(!password.equals(rePassword)) throw new RuntimeException("password no match");
        String hashedPassword=passwordEncoder.encode(password);
        AppUser appUser=new AppUser();
        appUser.setUserId(UUID.randomUUID().toString());
        appUser.setUsername(username);
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
       appRole.setDescription("role description");
       AppRole savedRole=appRoleRepository.save(appRole);
       return savedRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
     AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
     AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("user not found");
     appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException("user not found");
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
