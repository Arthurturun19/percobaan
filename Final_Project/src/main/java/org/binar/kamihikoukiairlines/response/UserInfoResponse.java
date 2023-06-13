package org.binar.kamihikoukiairlines.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {
    private String response;
    private String message;
    private String token;


    public UserInfoResponse(String response, String message, String token) {
        this.response = response;
        this.message = message;
        this.token = token;
    }
}
