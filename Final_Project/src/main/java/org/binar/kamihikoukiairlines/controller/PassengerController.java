package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.dto.PassengerRequest;
import org.binar.kamihikoukiairlines.model.Passenger;
import org.binar.kamihikoukiairlines.model.Schedule;
import org.binar.kamihikoukiairlines.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passenger")
@Tag(name = "Passenger", description = "Passenger Controller | contains : Add Passenger, Add All Passenger")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @GetMapping
    public List<Passenger> getALlSchedule(){
        return passengerService.getAllPassenger();
    }

    @PostMapping("/addPassenger")
    public ResponseEntity<?> addPassenger(@RequestBody PassengerRequest passengerRequest) {
        try {
            Passenger newPassenger = passengerService.addPassenger(passengerRequest);
            return ResponseEntity.ok(newPassenger);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /*@PostMapping("/addAllPassengers")
    public ResponseEntity<List<Passenger>> addAllPassengers(@RequestBody List<PassengerRequest> passengers) {
        try {
            List<Passenger> addedPassengers = passengerService.addPassengers(passengers);
            return ResponseEntity.ok().body(addedPassengers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }*/

    /*@PostMapping("/addPassenger")
    public ResponseEntity<List<Passenger>> addAllPassenger(@RequestBody List<Passenger> passengers) {
        List<Passenger> allPassengers = passengerService.addAllPassenger(passengers);
        return ResponseEntity.ok(allPassengers);
    }*/

}