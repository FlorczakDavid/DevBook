package co.simplon.devbookapi.repositories;

import co.simplon.devbookapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    Set<Role> findByRoleDefaultTrue();

}
