package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.dto.PaymentDTO;
import org.binar.kamihikoukiairlines.model.Booking;
import org.binar.kamihikoukiairlines.repository.BookingRepository;
import org.binar.kamihikoukiairlines.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
@Tag(name = "Booking", description = "Booking Controller | contains : Add Booking, Get All Booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/getAllBooking")
    public List<Booking> getAllBooking(){
        return bookingService.getAllBooking();
    }

    @GetMapping
    public List<Booking> getBookingByBookingCode(@RequestParam("bookingCode") String bookingCode){
        return bookingService.getBookingByBookingCode(bookingCode);
    }

    @PostMapping("/addBooking")
    public ResponseEntity<Booking> bookTicket(@RequestParam("user_id") Long userId,
                                              @RequestParam("schedule_id") Long scheduleId) {
        try {
            Booking newBooking = bookingService.bookTicket(userId, scheduleId);
            return ResponseEntity.ok(newBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentDTO paymentDTO) {
        try {
            Booking booking = bookingService.payment(paymentDTO);
            return ResponseEntity.ok(booking);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}