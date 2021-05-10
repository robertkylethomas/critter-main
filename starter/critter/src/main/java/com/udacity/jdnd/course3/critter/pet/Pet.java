package com.udacity.jdnd.course3.critter.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer.Customer;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private PetType type;

  private String name;

  @ManyToOne
  @LazyCollection(LazyCollectionOption.TRUE)
  @JoinColumn(name="owner_id")
  @JsonIgnoreProperties("pets")
  private Customer owner;

  private LocalDate birthdate;

  @Column(length = 3000)
  private String notes;

  @ManyToMany
  private List<Schedule> schedules = new ArrayList<>();

  @Override
  public String toString() {
    return "Pet{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", birthdate=" + birthdate +
      ", notes='" + notes + '\'' +
      '}';
  }
}
