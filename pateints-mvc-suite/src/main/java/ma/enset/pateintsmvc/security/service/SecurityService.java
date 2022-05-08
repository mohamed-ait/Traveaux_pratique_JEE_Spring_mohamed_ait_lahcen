package ma.enset.pateintsmvc.security.service;

import ma.enset.pateintsmvc.security.entities.Role;
import ma.enset.pateintsmvc.security.entities.User;

public interface SecurityService {
    User saveNewUser(String username , String password, String rePassword);
    Role saveNewRole(String roleName, String description);
    void addRoleToUser(String username,String roleName);
    void removeRoleFromUser(String username,String roleName);
    User loadUserByUserName(String username);
}
