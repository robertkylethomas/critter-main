package com.udacity.jdnd.course3.critter.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

  @Override
  public String toString() {
    return "PetDTO{" +
      "id=" + id +
      ", type=" + type +
      ", name='" + name + '\'' +
      ", ownerId=" + ownerId +
      ", birthDate=" + birthDate +
      ", notes='" + notes + '\'' +
      '}';
  }
}


