package ma.enset.pateintsmvc.security.repositories;

import ma.enset.pateintsmvc.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String roleName);
}
