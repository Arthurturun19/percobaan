package org.binar.kamihikoukiairlines.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassengerRequest {
    private String title;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String citizenship;
    private String passport;
    private String countryOfPublication;
    private Long bookingId;

}