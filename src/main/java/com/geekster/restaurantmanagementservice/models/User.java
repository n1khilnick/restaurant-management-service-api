package com.geekster.restaurantmanagementservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    @Column(nullable = false,unique = true)
    private String username;

    @NotNull
    private String userPassword;

    public User(String userFirstName, String userLastName, String userEmail, String username, String userPassword) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.username = username;
        this.userPassword = userPassword;
    }
}
