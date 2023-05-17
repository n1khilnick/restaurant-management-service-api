package com.geekster.restaurantmanagementservice.controllers;

import com.geekster.restaurantmanagementservice.models.Food;
import com.geekster.restaurantmanagementservice.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("viewer")
public class ViewerController {
    @Autowired
    FoodService foodService;

    @GetMapping("/menu")
    public Iterable<Food> menu(){
        return foodService.getAllFoods();
    }
}
