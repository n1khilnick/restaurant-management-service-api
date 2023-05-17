package com.geekster.restaurantmanagementservice.repositories;

import com.geekster.restaurantmanagementservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {

    User findFirstByUserEmail(String userEmail);
}
