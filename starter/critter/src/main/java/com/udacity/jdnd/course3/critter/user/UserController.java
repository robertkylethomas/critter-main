package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.Exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.user.Customer.Customer;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee.Employee;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
  CustomerService customerService;

    @Autowired
  EmployeeService employeeService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Long id = Optional.ofNullable(customerDTO.getId()).orElse(Long.valueOf(-1));
        Customer customer = customerService.findCustomer(id).orElseGet(Customer::new);
        BeanUtils.copyProperties(customerDTO, customer);
        List<Long> petIds = Optional.ofNullable(customerDTO.getPetIds()).orElseGet(ArrayList::new);
        customer = customerService.persistCustomer(customer, petIds);
        return copyCustomerToDTO(customer);
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id){
      Optional<Customer> c = customerService.findCustomer(id);
      CustomerDTO customerDTO = new CustomerDTO();
      List<Pet> allPets = new ArrayList<>();
      List<Long> petIds = new ArrayList<>();

      c.ifPresent(customer -> {
        customer.getPets().forEach(pet -> {
          petIds.add(pet.getId());
        });
      });

      c.ifPresent(customer -> {
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setId(customer.getId());
        customerDTO.setPetIds(petIds);
        customerDTO.setNotes(customer.getNotes());
      });

      System.out.println(customerDTO);

      return customerDTO;
    }
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
      List<CustomerDTO> customerDTOS = new ArrayList<>();
      List<Customer> customers = customerService.getAllCustomers();
      List<Long> petIds = new ArrayList<>();

      for (Customer customer : customers){
        customer.getPets().forEach(pet -> {
          petIds.add(pet.getId());
        });

        CustomerDTO customerDTO = new CustomerDTO(
          customer.getId(),
          customer.getName(),
          customer.getNotes(),
          customer.getPhoneNumber(),
          petIds);

        customerDTOS.add(customerDTO);
      }
      return customerDTOS;

    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){

      Customer customer = customerService.findOwnerByPetId(petId);

      List<Long> petIds = new ArrayList<>();
      customer.getPets().forEach(pet -> {
        petIds.add(pet.getId());
      });

      CustomerDTO customerDTO = new CustomerDTO(
        customer.getId(),
        customer.getName(),
        customer.getNotes(),
        customer.getPhoneNumber(),
        petIds);

      return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
      Employee employee = employeeService.findEmployee(employeeDTO.getId()).orElseGet(Employee::new);
      employee.setId(employeeDTO.getId());
      employee.setName(employeeDTO.getName());
      employee.setSkills(employeeDTO.getSkills());
      employee.setDaysAvailable(employeeDTO.getDaysAvailable());
      employee.setSkills(employeeDTO.getSkills());
      Employee employee1 = employeeService.persistEmployee(employee);
      EmployeeDTO employeeDTO1 = new EmployeeDTO(
        employee1.getId(),
        employee1.getName(),
        employee1.getSkills(),
        employee1.getDaysAvailable()
        );
      return employeeDTO1;
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees(){
      List<Employee> employees = employeeService.findAllEmployees();
      List<EmployeeDTO> employeeDTOS = new ArrayList<>();

      for (Employee employee : employees) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
          employee.getId(),
          employee.getName(),
          employee.getSkills(),
          employee.getDaysAvailable());

        employeeDTOS.add(employeeDTO);
      }



      return employeeDTOS;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Optional<Employee> employee = employeeService.findEmployee(employeeId);
        Employee emp = new Employee();
        employee.ifPresent(e -> {
          emp.setId(e.getId());
          emp.setName(e.getName());
          emp.setDaysAvailable(e.getDaysAvailable());
          emp.setSkills(e.getSkills());
        });

        return copyEmployeeToDTO(emp);
    }

    @Transactional
    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws EmployeeNotFoundException {
        Employee e = employeeService.findEmployee(employeeId).orElseThrow(() -> new EmployeeNotFoundException("ID: " + employeeId));
        e.setDaysAvailable(daysAvailable);
        employeeService.persistEmployee(e);
    }

    @GetMapping("/employee/availability")

    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
      List<Employee> employees = employeeService.findAvailable(employeeDTO.getSkills(), employeeDTO.getDate());

      List<EmployeeDTO> employeeDTOS = new ArrayList<>();
      employees.forEach(employee -> {
        EmployeeDTO employeeDTO1 = new EmployeeDTO(
          employee.getId(),
          employee.getName(),
          employee.getSkills(),
          employee.getDaysAvailable()
        );
        employeeDTOS.add(employeeDTO1);
        employeeDTOS.add(employeeDTO1);
      });
      return employeeDTOS;
    }

  private CustomerDTO copyCustomerToDTO(Customer c){
    CustomerDTO dto = new CustomerDTO();
    BeanUtils.copyProperties(c, dto);
    return dto;
  }

  private EmployeeDTO copyEmployeeToDTO(Employee e){
    EmployeeDTO dto = new EmployeeDTO();
    BeanUtils.copyProperties(e, dto);
    return dto;
  }

  private List<CustomerDTO> copyCustomersToDTOs (List<Customer> customers) {
    List dtos = new ArrayList<PetDTO>();
    customers.forEach( c -> {
      dtos.add(this.copyCustomerToDTO((Customer)c));
    });
    return dtos;
  }

}
