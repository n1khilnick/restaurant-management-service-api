package com.geekster.restaurantmanagementservice.controllers;
import com.geekster.restaurantmanagementservice.models.Admin;
import com.geekster.restaurantmanagementservice.models.Food;
import com.geekster.restaurantmanagementservice.services.AdminService;
import com.geekster.restaurantmanagementservice.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    FoodService foodService;

    @PostMapping("/")
    public void addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
    }

    @PostMapping("/food/email/{adminEmail}")
    public ResponseEntity<Void> addFood(@PathVariable String adminEmail, @RequestBody Food food){

        HttpStatus status;
        try {
            adminService.createFood(adminEmail,food);
            status = HttpStatus.OK;

        }catch (Exception ex){
            ex.printStackTrace();
            status = HttpStatus.FORBIDDEN;
        }
        return  new ResponseEntity<Void>(status);
    }

    @GetMapping("/menu")
    public Iterable<Food> menu(){
        return foodService.getAllFoods();
    }


    @DeleteMapping("/food/email/{adminEmail}/foodId/{foodId}")
    public ResponseEntity<String> deleteFood(@PathVariable String adminEmail, @PathVariable Long foodId){

        HttpStatus status;
        String response;
        try {
            adminService.removeFood(adminEmail,foodId);
            status = HttpStatus.OK;
            response = "Food with id "+foodId+" is deleted successfully !!";

        }catch (Exception ex){
            ex.printStackTrace();
            status = HttpStatus.FORBIDDEN;
            response = "Only admin is authorized!!";
        }
        return  new ResponseEntity<String>(response,status);
    }




}