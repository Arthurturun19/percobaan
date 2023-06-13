package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.dto.AirportRequest;
import org.binar.kamihikoukiairlines.model.Airport;
import org.binar.kamihikoukiairlines.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@Tag(name = "Airport", description = "Airport Controller | contains : Add Airport, Get All Airport, Update Airport, Delete Airport")
public class AirportController {
    @Autowired
    AirportService airportService;

    @PostMapping("/addAirport")
    public ResponseEntity<Airport> addAirport(@RequestBody AirportRequest airportRequest){
        Airport addAirport = airportService.addAirport(airportRequest);
        return new ResponseEntity<>(addAirport, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAirport")
    public ResponseEntity<List<Airport>> getAllAirport(){
        List<Airport> aircraftServices = airportService.getAllAirport();
        return ResponseEntity.ok(aircraftServices);
    }

    @PutMapping("/updateAirport/{airportId}")
    public ResponseEntity<String> updateAirport(@PathVariable(name = "airportId") Long id, @RequestBody Airport airport){
        airportService.updateAirport(id, airport);
        return new ResponseEntity<>("Data berhasil diupdate", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAirport/{airportId}")
    public ResponseEntity<String> deleteAirport(@PathVariable(name = "airportId") Long id) {
        airportService.deletAirport(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
