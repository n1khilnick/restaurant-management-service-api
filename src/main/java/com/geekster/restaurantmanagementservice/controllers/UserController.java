package com.geekster.restaurantmanagementservice.controllers;

import com.geekster.restaurantmanagementservice.dto.SignInInput;
import com.geekster.restaurantmanagementservice.dto.SignInOutput;
import com.geekster.restaurantmanagementservice.dto.SignUpOutput;
import com.geekster.restaurantmanagementservice.models.Food;
import com.geekster.restaurantmanagementservice.models.Order;
import com.geekster.restaurantmanagementservice.models.User;
import com.geekster.restaurantmanagementservice.models.enums.Status;
import com.geekster.restaurantmanagementservice.services.AuthTokenService;
import com.geekster.restaurantmanagementservice.services.FoodService;
import com.geekster.restaurantmanagementservice.services.OrderService;
import com.geekster.restaurantmanagementservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthTokenService authTokenService;

    @Autowired
    FoodService foodService;

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/signup")
    public SignUpOutput signUp(@RequestBody User signUpDto){
        return userService.signUp(signUpDto);
    }

    //sign-in

    @PostMapping(value = "/signin")
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }


    @DeleteMapping("/sign-out")
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg=null;

        if(authTokenService.authenticate(email,token))
        {
            authTokenService.deleteToken(token);
            msg = "Sign-out Successful";
            status = HttpStatus.OK;

        }
        else
        {
            msg = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @GetMapping("/menu")
    public Iterable<Food> menu(){
        return foodService.getAllFoods();
    }

    @PostMapping("/order/email/{email}/token/{token}")
    public ResponseEntity<String> createOrder(@PathVariable String email,@PathVariable String token ,@RequestBody Order order){
        HttpStatus status;
        String message;
        Status orderStatus;

        if(authTokenService.authenticate(email,token)){
            User user = authTokenService.findUserByToken(token);
            Optional<Food> food = foodService.getFoodById(Long.valueOf(order.getFood().getFoodId()));
            if(user.getUserEmail() == null){
                status  = HttpStatus.BAD_REQUEST;
                throw new IllegalStateException("User doesn't exists");

            }
            else if(food.isEmpty()) {

               throw new IllegalStateException("This food is not available right now !!");
            }
            else {
                order.setUser(user);
                orderService.addOrder(order);
                orderStatus = Status.ORDERED;
                message = "Your order is successfully " + orderStatus.toString().toLowerCase() + "!!";
                status = HttpStatus.OK;
            }
        }
        else {
            message = "Invalid User !!";
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<String>(message , status);
    }

    @GetMapping("/order/email/{email}/token/{token}")
    public ResponseEntity<Iterable<Order>> checkOrder(@PathVariable String email,@PathVariable String token){
        HttpStatus status;
        String msg = "";
        Iterable<Order> myOrders = new ArrayList<>();
        if(authTokenService.authenticate(email,token))
        {
            User user =  authTokenService.findUserByToken(token);
            myOrders = orderService.getAllOrdersByUserId(Long.valueOf(user.getUserId()));
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(myOrders, status);
    }

    @DeleteMapping("/order/email/{email}/token/{token}/orderId/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable String email,@PathVariable String token,@PathVariable Long orderId){
        HttpStatus status;
        String msg = "";
        Iterable<Order> myOrders = new ArrayList<>();
        if(authTokenService.authenticate(email,token))
        {
            orderService.cancelOrder(orderId);
            status = HttpStatus.OK;
            msg = "Order with id "+orderId+" is cancelled successfully !!";
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg, status);
    }



}
