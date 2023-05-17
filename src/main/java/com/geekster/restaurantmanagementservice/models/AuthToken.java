package com.geekster.restaurantmanagementservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    private String token;

    private LocalDate tokenCreationDate;

    @OneToOne
    @JoinColumn(name = "fk_user_id",nullable = false)
    private User user;

    public AuthToken(User user) {
        this.user = user;
        this.tokenCreationDate = LocalDate.now();
        this.token = UUID.randomUUID().toString();
    }
}
