package com.g6.Rental.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g6.Rental.entity.Property;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
     List<Property> findByUser_Id(Long userId);
     Optional<Property> findByIdAndUser_Id(Long propertyId, Long userId); 
}