package com.geekster.restaurantmanagementservice.services;

import com.geekster.restaurantmanagementservice.models.Food;
import com.geekster.restaurantmanagementservice.repositories.IFoodDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    IFoodDao foodDao;

    public void createFood(Food Food) {
        foodDao.save(Food);
    }

    public Iterable<Food> getAllFoods() {
        return foodDao.findAll();
    }

    public void addFood(String adminEmail, Food food) {
        foodDao.save(food);
    }

    @Transactional
    public void deleteFoodById(Long foodId) {
        foodDao.deleteById(foodId);
    }


    public Optional<Food> getFoodById(Long foodId) {
        return foodDao.findById(foodId);
    }
}
