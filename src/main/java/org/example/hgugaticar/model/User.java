package org.example.hgugaticar.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")  // 테이블명 명시
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")  // DB 컬럼명과 매칭
    private String username;

    @Column(name = "password")
    private String password;
}