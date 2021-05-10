package com.udacity.jdnd.course3.critter.user.Employee;

import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
public class Employee extends User {

  @ElementCollection
  @CollectionTable(
    name = "employee_skill",
    joinColumns = @JoinColumn(name = "id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "SKILL" })
  )
  @Column(name="skill")
  private Set<EmployeeSkill> skills;

  @ElementCollection
  @CollectionTable(
    name="day_of_week",
    joinColumns = @JoinColumn(name="id"), uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "DAY"}))
  @Column(name="day")
  private Set<DayOfWeek> daysAvailable;

  @ManyToMany(mappedBy = "employees")
  private List<Schedule> scheduleList = new ArrayList<>();



}
