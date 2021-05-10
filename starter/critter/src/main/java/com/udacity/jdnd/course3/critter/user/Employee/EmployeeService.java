package com.udacity.jdnd.course3.critter.user.Employee;

import com.udacity.jdnd.course3.critter.Exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  ManagedEmployeeRepo managedEmployeeRepo;

  public Optional<Employee> findEmployee(Long id) throws EmployeeNotFoundException {
    return employeeRepository.findById(id);
  }

  public List<Employee> findEmployees(List<Long> employeeIds){
    List<Employee> employees = employeeRepository.findAllById(employeeIds);

    if (employeeIds.size() != employees.size()) {
      List<Long> found = employees.stream().map(e -> e.getId()).collect(Collectors.toList());
      String missing = (String) employeeIds
        .stream()
        .filter( id -> !found.contains(id) )
        .map(String::valueOf)
        .collect(Collectors.joining(", "));
    }

    return employees;
  }

  public List<Employee> findAvailable(Set<EmployeeSkill> skills, LocalDate date){
    // returns a list of employee ids
    List<Long> employeeIds = managedEmployeeRepo.findEmployeeIdsWithAllSkillsOnDay(skills, date.getDayOfWeek());
    List<Employee> employees = employeeRepository.findAllById(employeeIds);
    return employees;
  }

  public List<Employee> findAllEmployeesById(List<Long> employeeIds){
    List<Employee> employees = new ArrayList<>();

    employeeIds.forEach(employeeId -> {
      employeeRepository.findById(employeeId).ifPresent( employee -> {
        employees.add(employee);
      } );
    });

    System.out.println(employees);

    return employees;
  }

  public List<Employee>findAllEmployees(){
    return employeeRepository.findAll();
  }

  @Transactional
  public Employee persistEmployee(Employee employee){
    Employee e= employeeRepository.save(employee);
    return e;
  }




}
