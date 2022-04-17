package ma.enset.pateintsmvc.security.repositories;

import ma.enset.pateintsmvc.security.entities.AppRole;
import ma.enset.pateintsmvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
