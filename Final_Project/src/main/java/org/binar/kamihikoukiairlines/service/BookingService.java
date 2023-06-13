package org.binar.kamihikoukiairlines.service;

import nonapi.io.github.classgraph.utils.VersionFinder;
import org.binar.kamihikoukiairlines.dto.PaymentDTO;
import org.binar.kamihikoukiairlines.model.Booking;
import org.binar.kamihikoukiairlines.model.Schedule;
import org.binar.kamihikoukiairlines.model.TransactionHistory;
import org.binar.kamihikoukiairlines.model.Users;
import org.binar.kamihikoukiairlines.repository.BookingRepository;
import org.binar.kamihikoukiairlines.repository.ScheduleRepository;
import org.binar.kamihikoukiairlines.repository.TransactionHistoryRepository;
import org.binar.kamihikoukiairlines.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;


    public List<Booking> getAllBooking(){
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingByBookingCode(String bookingCode){
        List<Booking> booking = bookingRepository.findBookingByBookingCode(bookingCode);
        return booking;
    }

    @Transactional
    public Booking bookTicket(Long userId, Long scheduleId) throws Exception {
        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Route not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new Exception("Route not found"));

        LocalDateTime dueDate = LocalDateTime.now().plusHours(2);
        Booking booking = new Booking();
        booking.setUsers(users);
        booking.setSchedule(schedule);
        booking.setBookingCode(generateBookingCode());
        booking.setDueValid(dueDate);
        booking.setIsSuccess(false);
        booking.setIsValid(true);

        bookingRepository.save(booking);

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setUsers(users);
        transactionHistory.setBooking(booking);
        transactionHistory.setTransactionDate(LocalDateTime.now());
        transactionHistoryRepository.save(transactionHistory);

        return booking;
    }

    public Booking payment(PaymentDTO paymentDTO) {
        Booking booking = bookingRepository.findById(paymentDTO.getBookingId()).orElseThrow(() -> {
            throw new NotFoundException("Booking Id Not Found");
        });

        if (Boolean.TRUE.equals(booking.getIsSuccess())){
            HashMap<String, String> errorMessage = new HashMap<>();
            errorMessage.put("ERROR", "booking with "+booking.getBookingCode() + " code has successfully paid");
            throw new RuntimeException(String.valueOf(errorMessage));
        }else {
            if (LocalDateTime.now().isAfter(booking.getDueValid())) {
                booking.setIsValid(false);
                HashMap<String, String> errorMessage = new HashMap<>();
                errorMessage.put("ERROR", "booking code is invalid");
                throw new RuntimeException(String.valueOf(errorMessage));
            }else {
                booking.setIsSuccess(true);
                booking.setIsValid(false);
                booking.setPaymentMethod(paymentDTO.getPaymentMethod());
            }
        }
        return bookingRepository.save(booking);
    }

    private String generateBookingCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

}