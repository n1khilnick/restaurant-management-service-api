package com.geekster.restaurantmanagementservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String adminFirstName;

    private String adminLastName;
    @Email(regexp = "^[A-Za-z0-9._%+-]+@admin\\.com$",message = "email should be in format of user@admin.com")
    @Column(nullable = false ,unique = true)
    private String adminEmail;

    @Column(nullable = false ,unique = true)
    private String adminUserName;





}
