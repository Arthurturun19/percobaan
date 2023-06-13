package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
