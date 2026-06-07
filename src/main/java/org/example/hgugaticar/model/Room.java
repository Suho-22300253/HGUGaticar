package org.example.hgugaticar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure")
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "max_capacity")
    private int maxCapacity = 4;

    @Column(name = "current_capacity")
    private int currentCapacity = 1;

    @Enumerated(EnumType.STRING)
    @Column(name = "route_type")
    private RouteType routeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoomStatus status = RoomStatus.OPEN;

    @ManyToOne
    @JoinColumn(name = "leader_id")
    private User leader;
}