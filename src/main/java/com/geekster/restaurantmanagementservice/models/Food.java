package com.geekster.restaurantmanagementservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.converters.models.MonetaryAmount;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="foodTable")
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    @NotNull
    private String foodTitle;

    private String foodDescription;

    @NotNull
    private Double price;

    private String image;







}
