package com.udacity.jdnd.course3.critter.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT pet.* FROM pet " +
      "INNER JOIN customer on customer.id = pet.owner_id " +
      "where pet.owner_id = :id", nativeQuery = true)
    List<Pet> findPetsByCutsomerId(@Param("id") Long id);
}
