package org.example.hgugaticar.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "room")  // 테이블명 명시
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure")      // 컬럼명 명시
    private String departure;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "max_capacity")  // DB 컬럼명과 매칭
    private int maxCapacity = 4;

    @ManyToOne
    @JoinColumn(name = "leader_id")  // 외래키 컬럼명 명시
    private User leader;
}