package org.factoriaf5.happypaws.role;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    
    public Optional<RoleEntity> findByName(String name);
}
