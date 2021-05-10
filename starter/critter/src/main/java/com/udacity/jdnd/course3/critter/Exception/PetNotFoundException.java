package com.udacity.jdnd.course3.critter.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PetNotFoundException extends RuntimeException {
  public PetNotFoundException(String message){
    super(message);
  }
}
