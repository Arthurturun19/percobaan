package org.binar.kamihikoukiairlines.service;

import org.binar.kamihikoukiairlines.model.Aircraft;
import org.binar.kamihikoukiairlines.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {

    @Autowired
    AircraftRepository aircraftRepository;


    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }


    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Aircraft addAircraft(Aircraft aircraft){
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long id, Aircraft aircraft){
        Aircraft pesawat = aircraftRepository.findById(id).get();
        pesawat.setAircraftName(aircraft.getAircraftName());
        return aircraftRepository.save(pesawat);
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }
}
