package ma.enset.jpaenset.service;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    //Role findByRoleName(String roleName);
    void addRoleToUser(String userName,String roleName);
    User authenticate(String username,String password);

}
