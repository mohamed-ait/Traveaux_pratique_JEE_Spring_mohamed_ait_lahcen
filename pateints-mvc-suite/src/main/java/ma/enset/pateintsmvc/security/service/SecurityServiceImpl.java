package ma.enset.pateintsmvc.security.service;

import ma.enset.pateintsmvc.security.entities.AppRole;
import ma.enset.pateintsmvc.security.entities.AppUser;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public AppUser saveNewUser(String username, String password, String rePassword) {
        return null;
    }

    @Override
    public AppRole saveNewRole(String roleName, String description) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {

    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return null;
    }
}
