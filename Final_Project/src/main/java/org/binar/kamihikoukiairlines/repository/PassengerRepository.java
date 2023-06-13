package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
