package com.geekster.restaurantmanagementservice.repositories;

import com.geekster.restaurantmanagementservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDao extends JpaRepository<Order,Integer> {

    @Query(value = "select * from orders where fk_user_user_id = :userId",nativeQuery = true)
    Iterable<Order> findAllOrderByUserId(Long userId);
}
