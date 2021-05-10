package com.udacity.jdnd.course3.critter.user.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
  private Long id;
  private String name;
  private String phoneNumber;
  private String notes;
  private List<Long> petIds = new ArrayList<>();

}
