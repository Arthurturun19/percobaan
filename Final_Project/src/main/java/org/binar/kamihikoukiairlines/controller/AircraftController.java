package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.model.Aircraft;
import org.binar.kamihikoukiairlines.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
@Tag(name = "Aircraft", description = "Aircraft Controller | contains : Add Aircraft, Get All Aircraft, Update Aircraft, Delete Aircraft")
public class AircraftController {
    @Autowired
    AircraftService aircraftService;

    @PostMapping("/addAircraft")
    public ResponseEntity<Aircraft> addAircraft(@RequestBody Aircraft aircraft){
        Aircraft addAircraft = aircraftService.addAircraft(aircraft);
        return new ResponseEntity<>(addAircraft, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAircraft")
    public ResponseEntity<List<Aircraft>> getAllAircraft(){
        List<Aircraft> aircraft = aircraftService.getAllAircraft();
        return ResponseEntity.ok(aircraft);
    }

    @PutMapping("/updateAircraft/{aircraftId}")
    public ResponseEntity<String> updateAircraft(@PathVariable(name = "aircraftId") Long id, @RequestBody Aircraft aircraft){
        aircraftService.updateAircraft(id, aircraft);
        return new ResponseEntity<>("Data berhasil diupdate", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAircraft/{aircraftId}")
    public ResponseEntity<String> deleteAircraft(@PathVariable(name = "aircraftId") Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
