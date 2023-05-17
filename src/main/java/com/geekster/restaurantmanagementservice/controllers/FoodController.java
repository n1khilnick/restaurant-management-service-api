package com.geekster.restaurantmanagementservice.controllers;

import com.geekster.restaurantmanagementservice.models.Food;
import com.geekster.restaurantmanagementservice.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Food")
public class FoodController {
    @Autowired
    FoodService FoodService;

    @PostMapping(value = "/")
    public void addFood(@RequestBody Food Food){
        FoodService.createFood(Food);
    }

    @GetMapping(value = "/")
    public Iterable<Food> getAllFoods(){
        return FoodService.getAllFoods();
    }

//    @GetMapping(value = "/course/{courseId}")
//    public ResponseEntity<List<Food>> getFoodsByCourseId(@PathVariable Integer courseId)
//    {
//        List<Food> myFoods = (List<Food>) FoodService.fetchFoodByCourseId(courseId);
//
//        if(myFoods.isEmpty()){
//            throw new IllegalStateException("Id "+courseId+" doesn't exists !!");
//        }
//        else{
//            return new ResponseEntity<>(myFoods, HttpStatus.OK);
//        }
//    }

}
