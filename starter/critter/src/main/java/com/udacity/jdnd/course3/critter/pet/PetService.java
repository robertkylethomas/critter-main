package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.Exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer.Customer;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  PetRepository petRepository;

  @Autowired
  CustomerService customerService;

  @Transactional
  public Pet persistPet(Pet pet, Long ownerId) throws CustomerNotFoundException{
    Customer owner = customerRepository.findById(ownerId)
      .orElseThrow(() -> new CustomerNotFoundException("Customer with ID: " + ownerId + " not found"));
    pet.setOwner(owner);
    pet = petRepository.save(pet);
    customerService.addPetToCustomer(pet,pet.getOwner());
    owner.getPets().add(pet);
    customerRepository.save(owner);
    return pet;
  }

  public Optional<Pet> findPet(Long id) throws PetNotFoundException {
    return
      petRepository.findById(id);
  }

  public List<Pet> findAllPets(){
    return petRepository.findAll();
  }

  public List<Pet> findAllPetsById(List<Long> petIds){
    List<Pet> pets = petRepository.findAllById(petIds);

    if (petIds.size() != pets.size()) {
      List<Long> found = pets.stream().map(p -> p.getId()).collect(Collectors.toList());
      String missing = (String) petIds
        .stream()
        .filter( id -> !found.contains(id) )
        .map(String::valueOf)
        .collect(Collectors.joining(", "));
      throw new PetNotFoundException("Could not find pet(s) with id(s): " + missing);
    }
    return pets;
  }

  public List<Pet> findPetsByOwnerID(Long ownerId){
    List<Pet> pets =  petRepository.findPetsByCutsomerId(ownerId);
    System.out.println("form the service");
    System.out.println(pets);
    return pets;
  }
}
