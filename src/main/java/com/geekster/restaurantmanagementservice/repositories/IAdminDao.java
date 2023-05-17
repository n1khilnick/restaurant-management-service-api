
package com.geekster.restaurantmanagementservice.repositories;

import com.geekster.restaurantmanagementservice.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAdminDao extends JpaRepository<Admin,Long> {
    Admin findAdminByAdminEmail(String adminEmail);

}