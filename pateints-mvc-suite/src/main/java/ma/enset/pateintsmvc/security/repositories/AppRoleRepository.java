package ma.enset.pateintsmvc.security.repositories;

import ma.enset.pateintsmvc.security.entities.AppUser;

public interface AppRoleRepository {
    AppUser findByRoleName(String roleName);
}
