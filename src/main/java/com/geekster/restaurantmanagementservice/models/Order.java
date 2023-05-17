package com.geekster.restaurantmanagementservice.models;

import com.geekster.restaurantmanagementservice.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_user_userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_food_foodId")
    private Food food;



}
