package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Exception.CustomerNotFoundException;
import com.udacity.jdnd.course3.critter.Exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.Exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer.Customer;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee.Employee;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

  @Autowired
  ScheduleRepository scheduleRepository;

  @Autowired
  CustomerService customerService;

  @Autowired
  PetService petService;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  PetRepository petRepository;

  @Transactional
  public Schedule persist(Schedule schedule){
    schedule = scheduleRepository.save(schedule);
    for(Employee employee: schedule.getEmployees()){
      employee.getScheduleList().add(schedule);
      employeeService.persistEmployee(employee);
    }
    for (Pet pet : schedule.getPets()){
      pet.getSchedules().add(schedule);
      petRepository.save(pet);
    }
    return schedule;
  }

  public Optional<Schedule> findSchedule(Long id){
    Optional<Schedule> schedule = scheduleRepository.findById(id);
    return schedule;
  }

  public List<Schedule> findSchedulesForPet(long petId){
    Pet p = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException());
    return p.getSchedules();
  }

  public List<Schedule> findSchedulesForEmployee(long employeeId) {

    Employee e = employeeService.findEmployee(employeeId)
      .orElseThrow(() -> new EmployeeNotFoundException("ID: " + employeeId));
    return e.getScheduleList();
  }

  public List<Schedule> findAllSchedules(){
    return scheduleRepository.findAll();
  }

  public List<Schedule> findSchedulesForCustomer(Long customerId){
    Customer customer = customerService.findCustomer(customerId)
      .orElseThrow(() -> new CustomerNotFoundException(("ID: " + customerId)));

    List<Schedule> customerSchedules = new ArrayList<>();

    List<Pet> pets = customer.getPets();

    pets.forEach(pet -> {
      pet.getSchedules().forEach(schedule -> {
        customerSchedules.add(schedule);
      });
    });


    return customerSchedules;

  }
}
