package com.geekster.restaurantmanagementservice.services;
import com.geekster.restaurantmanagementservice.models.Order;
import com.geekster.restaurantmanagementservice.models.User;
import com.geekster.restaurantmanagementservice.repositories.IOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    IOrderDao orderDao;

//    public void createOrder(Order order) {
//        User user = order.getUser();
//        Address address = order.getAddress();
//        address.setUser(user);
//        orderDao.save(order);
//    }

    public Iterable<Order> getAllOrders() {
        return orderDao.findAll();
    }

    public Optional<Order> getOrderById(Integer orderId) {
        return orderDao.findById(orderId);
    }

    public void addOrder(Order order) {
        orderDao.save(order);
    }

    public Iterable<Order> getAllOrdersByUserId(Long userId) {

        return orderDao.findAllOrderByUserId(userId);
    }

    public void cancelOrder(Long orderId) {
        orderDao.deleteById(Math.toIntExact(orderId));
    }
}
