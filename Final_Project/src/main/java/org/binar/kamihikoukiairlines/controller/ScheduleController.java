package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.dto.ScheduleRequest;
import org.binar.kamihikoukiairlines.model.Schedule;
import org.binar.kamihikoukiairlines.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedule")
@Tag(name = "Schedule", description = "Schedule Controller | contains : Add Schedule, Get All Schedule, Get Schedule By Id")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("/addSchedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        try {
            Schedule newSchedule = scheduleService.addSchedule(scheduleRequest);
            return ResponseEntity.ok(newSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getAllSchedule")
    public List<Schedule> getALlSchedule(){
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/getScheduleById/{scheduleId}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable(value = "scheduleId") Long id) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Schedule>> searchFlightWithoutArrival(@RequestParam("departure") String departure,
                                                                     @RequestParam("arrival") String arrival,
                                                                     @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                                     @RequestParam("seatClass") String seatClass,
                                                                     @RequestParam(value = "page", defaultValue = "0") int page,
                                                                     @RequestParam(value = "size", defaultValue = "10") int size){
        Page<Schedule> flights = scheduleService.searchFlightWithoutArrival(departure, arrival, date, seatClass, page, size);
        return ResponseEntity.ok(flights);
    }


}
