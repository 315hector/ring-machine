package pnp.ringmachine.engine.repository;

import org.springframework.data.repository.CrudRepository;
import pnp.ringmachine.engine.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
