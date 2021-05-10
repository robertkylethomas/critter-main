package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM Customer AS customer " +
                    "INNER JOIN User AS user ON user.id = customer.id " +
                    "INNER JOIN Pet AS pet ON customer.id = pet.owner_id " +
                    "WHERE pet.id = :id", nativeQuery = true)
    Optional<Customer> findOptionalByPetId(@Param("id") Long id);
}
