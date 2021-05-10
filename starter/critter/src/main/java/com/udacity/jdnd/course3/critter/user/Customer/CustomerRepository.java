package com.udacity.jdnd.course3.critter.user.Customer;


import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query(value = "SELECT distinct customer.* FROM customer" +
    "    INNER JOIN pet on customer.id = pet.owner_id" +
    "    where pet.owner_id = :id", nativeQuery = true)
  Customer findOwnerByPetId(@Param("id") Long id);

}
