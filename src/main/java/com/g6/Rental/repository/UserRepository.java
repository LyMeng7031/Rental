package com.g6.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.g6.Rental.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
