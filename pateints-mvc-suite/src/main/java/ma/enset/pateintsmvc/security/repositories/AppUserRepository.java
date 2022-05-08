package ma.enset.pateintsmvc.security.repositories;

import ma.enset.pateintsmvc.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
