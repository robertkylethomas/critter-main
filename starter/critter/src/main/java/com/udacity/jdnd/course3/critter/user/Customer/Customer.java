package com.udacity.jdnd.course3.critter.user.Customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.User;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends User {

  private String phoneNumber;

  @Column(length=3000)
  private String notes;

  @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @JsonIgnoreProperties("owner")
  @JsonManagedReference
  private List<Pet> pets = new ArrayList<>();



}
