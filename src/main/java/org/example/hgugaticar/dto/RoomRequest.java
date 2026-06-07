package org.example.hgugaticar.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hgugaticar.model.RouteType;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class RoomRequest {

    private String departure;
    private String arrival;
    private LocalDate departureDate;
    private LocalTime time;
    private int maxCapacity;
    private Long leaderId;
    private RouteType routeType;
}