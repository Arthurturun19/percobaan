package org.binar.kamihikoukiairlines.dto;

import lombok.Data;

@Data
public class EditProfileRequest {
    private String name;
    private String phoneNumber;
    private String email;

}
