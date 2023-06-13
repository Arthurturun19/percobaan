package org.binar.kamihikoukiairlines.dto;

import lombok.Data;
import org.binar.kamihikoukiairlines.model.Schedule;
import org.binar.kamihikoukiairlines.model.Users;

import javax.validation.constraints.NotBlank;

@Data
public class BookingRequest {

    @NotBlank
    private Users users;
    @NotBlank
    private Schedule scheduleId;

}