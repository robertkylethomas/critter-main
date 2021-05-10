package com.udacity.jdnd.course3.critter.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {
  public EmployeeNotFoundException(String message){
    super(message);
  }
}
