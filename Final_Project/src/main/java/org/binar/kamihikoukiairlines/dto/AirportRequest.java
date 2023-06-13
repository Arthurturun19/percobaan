package org.binar.kamihikoukiairlines.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AirportRequest {
    @NotBlank
    private String airportName;
    @NotBlank
    private String airportCode;
    @NotBlank
    private String cityName;
    @NotBlank
    private String cityCode;
    @NotBlank
    private String countryName;
    @NotBlank
    private String countryCode;
}
