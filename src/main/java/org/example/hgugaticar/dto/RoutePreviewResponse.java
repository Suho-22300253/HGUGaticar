package org.example.hgugaticar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.hgugaticar.model.RouteType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RoutePreviewResponse {

    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private RouteType routeType;
    private String message;
}