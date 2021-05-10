package com.udacity.jdnd.course3.critter.user.Customer;

import com.udacity.jdnd.course3.critter.Exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.Exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee.Employee;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  PetRepository petRepository;

  // Use optional because customer may not exist
  public Optional<Customer> findCustomer(Long id){
    return customerRepository.findById(id);
  }

  @Transactional
  public Customer persistCustomer(Customer customer, List<Long> petIds) {
    // ensure that there are no pets that exist for a new Customer
    customer.getPets().clear();
    for(Long petId: petIds){
      Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException("ID: " + petId));
      customer.getPets().add(pet);
    }

    return customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers(){
    List<Customer> customers =  customerRepository.findAll();
    return customers;
  }

public List<Pet> findPetByOwnerId(Long ownerId) throws CustomerNotFoundException {
    List<Pet> pets = petRepository.findPetsByCutsomerId(ownerId);
    return pets;
}

public Customer findOwnerByPetId(Long petId) throws PetNotFoundException{
    Customer customer = customerRepository.findOwnerByPetId(petId);



    return customer;
}

  public void addPetToCustomer(Pet pet, Customer customer) { List<Pet> pets = customer.getPets();
    if (pets != null)
      pets.add(pet);
    else {
      pets = new ArrayList<Pet>();
      pets.add(pet);
    }
    customer.setPets(pets);
    customerRepository.save(customer);
  }


}
