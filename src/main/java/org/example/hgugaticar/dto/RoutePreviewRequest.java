package org.example.hgugaticar.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RoutePreviewRequest {

    private String departure;
    private String arrival;
    private LocalDate departureDate;
}