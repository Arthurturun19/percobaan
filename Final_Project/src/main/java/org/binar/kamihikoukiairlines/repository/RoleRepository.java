package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.ERole;
import org.binar.kamihikoukiairlines.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
