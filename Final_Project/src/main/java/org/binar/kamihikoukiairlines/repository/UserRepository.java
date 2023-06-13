package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByName(String name);

    Boolean existsByEmail(String email);

    Optional<Users> findByEmail(String email);


}
