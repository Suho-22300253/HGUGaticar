package org.example.hgugaticar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 장소 이름: 카페 유야, 투다리, 그할마, 법원, 다이소
    @Column(name = "name", nullable = false)
    private String name;

    // 주요 장소 여부
    @Column(name = "main_place")
    private boolean mainPlace = true;
}