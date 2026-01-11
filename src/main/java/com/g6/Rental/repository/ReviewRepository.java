package com.g6.Rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g6.Rental.entity.Property;
import com.g6.Rental.entity.Review;
import com.g6.Rental.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByUserAndProperty(User user, Property property);

    List<Review> findByPropertyId(Long propertyId);

}
