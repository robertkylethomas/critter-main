package com.udacity.jdnd.course3.critter.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message){
    super(message);
  }
}
