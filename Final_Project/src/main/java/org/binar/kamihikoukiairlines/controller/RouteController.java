package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.model.Route;
import org.binar.kamihikoukiairlines.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/route")
@Tag(name = "Route", description = "Route Controller | contains : Add Route, Get All Route, Update Route, Delete Route")
public class RouteController {
    @Autowired
    RouteService routeService;

    @PostMapping("/addRoute")
    public ResponseEntity<?> addRoute(@RequestParam("departureAirportId") Long departureAirportId,
                                      @RequestParam("arrivalAirportId") Long arrivalAirportId,
                                      @RequestParam("aircraftId") Long aircraftId) throws Exception {
        Route createdRoute = routeService.addRoute(departureAirportId, arrivalAirportId, aircraftId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoute);
    }

    @GetMapping("/getAllRoute")
    public ResponseEntity<?> getAllRoute(){
        List<Route> findRoute = routeService.getAllRoute();
        return ResponseEntity.ok(findRoute);
    }

    @GetMapping("/getRouteById/{routeId}")
    public ResponseEntity<Route> getScheduleById(@PathVariable(value = "routeId") Long id) {
        Optional<Route> schedule = routeService.getRouteById(id);
        if (schedule.isPresent()) {
            return new ResponseEntity<>(schedule.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateRoute/{routeId}")
    public ResponseEntity<Route> updateRoute(
            @PathVariable(name = "routeId") Long routeId,
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam Long aircraftId
    ) {
        try {
            Route updatedRoute = routeService.updateRoute(routeId, departureAirportId, arrivalAirportId, aircraftId);
            return ResponseEntity.ok(updatedRoute);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/deleteRoute/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable(name = "routeId") Long id) {
        routeService.deletRoute(id);
        return ResponseEntity.ok("Data Berhasil dihapus");
    }
}
