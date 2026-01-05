package com.g6.Rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g6.Rental.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
