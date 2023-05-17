package com.geekster.restaurantmanagementservice.repositories;

import com.geekster.restaurantmanagementservice.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodDao extends JpaRepository<Food,Long> {
}
