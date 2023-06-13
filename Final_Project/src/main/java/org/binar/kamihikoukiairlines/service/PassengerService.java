package org.binar.kamihikoukiairlines.service;

import org.binar.kamihikoukiairlines.dto.PassengerRequest;
import org.binar.kamihikoukiairlines.model.Booking;
import org.binar.kamihikoukiairlines.model.Passenger;
import org.binar.kamihikoukiairlines.model.Schedule;
import org.binar.kamihikoukiairlines.repository.BookingRepository;
import org.binar.kamihikoukiairlines.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    BookingRepository bookingRepository;


    public List<Passenger> getAllPassenger() {
        return passengerRepository.findAll();
    }

    @Transactional
    public Passenger addPassenger(PassengerRequest passenger) throws Exception{

        Booking findBooking = bookingRepository.findById(passenger.getBookingId())
                .orElseThrow(() -> new Exception("Booking not found"));
        Passenger passenger1 = new Passenger();
        passenger1.setTitle(passenger.getTitle());
        passenger1.setName(passenger.getName());
        passenger1.setSurname(passenger.getSurname());
        passenger1.setBirthday(passenger.getBirthday());
        passenger1.setCitizenship(passenger.getCitizenship());
        passenger1.setPassport(passenger.getPassport());
        passenger1.setCountryOfPublication(passenger.getCountryOfPublication());
        passenger1.setBooking(findBooking);

        return passengerRepository.save(passenger1);
    }

    /*@Transactional
    public List<Passenger> addPassengers(List<PassengerRequest> passengers) throws Exception {
        List<Passenger> addedPassengers = new ArrayList<>();

        for (PassengerRequest passengerRequest : passengers) {
            Booking findBooking = bookingRepository.findById(passengerRequest.getBookingId())
                    .orElseThrow(() -> new Exception("Route not found"));

            Passenger passenger = new Passenger();
            passenger.setTitle(passengerRequest.getTitle());
            passenger.setName(passengerRequest.getName());
            passenger.setSurname(passengerRequest.getSurname());
            passenger.setBirthday(passengerRequest.getBirthday());
            passenger.setCitizenship(passengerRequest.getCitizenship());
            passenger.setPassport(passengerRequest.getPassport());
            passenger.setCountryOfPublication(passengerRequest.getCountryOfPublication());
            passenger.setBooking(findBooking);

            addedPassengers.add(passengerRepository.save(passenger));
        }
        return addedPassengers;
    }*/

    /*public List<Passenger> addAllPassenger(List<Passenger> passengers) {
        List<Passenger> allPassengers = passengerRepository.saveAll(passengers);
        return allPassengers;
    }*/
}