package com.udacity.jdnd.course3.critter.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee.Employee;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToMany
  @LazyCollection(LazyCollectionOption.TRUE)
  @JoinTable(
    name = "schedule_employee",
    joinColumns = { @JoinColumn(name="schedule_id") },
    inverseJoinColumns = { @JoinColumn(name = "employee_id") }
  )
  @JsonBackReference
  @JsonIgnoreProperties("schedules")
  private List<Employee> employees;

  @ManyToMany
  @LazyCollection(LazyCollectionOption.TRUE)
  @JoinTable(
    name = "schedule_pets",
    joinColumns = { @JoinColumn(name = "schedule_id") },
    inverseJoinColumns = { @JoinColumn(name = "pet_id") }
  )
  private List<Pet> pets;

  private LocalDate date;

  @ElementCollection
  @CollectionTable(
      name= "schedule_activities",
      joinColumns = @JoinColumn(name = "id")
  )
  @Column(name = "activities")
  private Set<EmployeeSkill> activies;

  @Override
  public String toString() {
    return "Schedule{" +
      "id=" + id +

      '}';
  }
}
