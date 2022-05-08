package ma.enset.jpaenset.service;

import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    @Override
    public void addRoleToUser(String userName, String roleName) {
     User user = userRepository.findByUserName(userName);
     Role role = roleRepository.findByRoleName(roleName);
     user.getRoles().add(role);
     userRepository.save(user);
    }
    @Override
    public User authenticate(String username, String password) {
        User user=userRepository.findByUserName(username);
        if(user==null)throw new RuntimeException("bad credential!");
        if(password.equals(user.getPassword())){
            return user;
        }
        throw new RuntimeException("bad credential!");
    }
}
