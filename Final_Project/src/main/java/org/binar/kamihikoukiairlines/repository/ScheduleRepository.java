package org.binar.kamihikoukiairlines.repository;

import org.binar.kamihikoukiairlines.model.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.route r WHERE r.departure.airportName = :departure AND r.arrival.airportName = :arrival AND s.departureDate = :departureDate AND s.seatClass = :seatClass")
    Page<Schedule> findByDepartureAndArrivalAndDepartureDateAndSeatClass(
            @Param("departure") String departure, @Param("arrival") String arrival,
            @Param("departureDate") LocalDate departureDate, @Param("seatClass") String seatClass, Pageable pageable);

}
