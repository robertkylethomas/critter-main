package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.Exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.Customer.Customer;
import com.udacity.jdnd.course3.critter.user.Customer.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

  @Autowired
  PetService petService;

  @Autowired
  CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
     // check if the id of the pet is null
     long petId = Optional.ofNullable(petDTO.getId()).orElse(-1L);

     // get the pet if it exists if not create one
      Pet pet = petService.findPet(petId).orElseGet(Pet::new);

      pet = petService.persistPet(copyDTOToPet(petDTO), petDTO.getOwnerId());
      PetDTO petDTO1 = new PetDTO(
        pet.getId(),
        pet.getType(),
        pet.getName(),
        petDTO.getOwnerId(),
        pet.getBirthdate(),
        pet.getNotes());
      return petDTO1;
    }


    @GetMapping("/{petId}")
    public PetDTO find(@PathVariable long petId) throws PetNotFoundException {
//      Optional<Pet> pet = petService.findPet(petId).orElseThrow(() -> new PetNotFoundException("Pet not found"));;
      Optional<Pet> pet = petService.findPet(petId);

      PetDTO petDTO = new PetDTO();
      pet.ifPresent( pet1 -> {
          petDTO.setOwnerId(pet1.getOwner().getId());
          petDTO.setName(pet1.getName());
          petDTO.setId(pet1.getId());
          petDTO.setBirthDate(pet1.getBirthdate());
          petDTO.setType(pet1.getType());
          petDTO.setNotes(pet1.getNotes());
      });

      pet.orElseThrow(() -> new PetNotFoundException("Pet with ID: " + petId + " not found"));
      return petDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
        return copyPetsToDTOs(pets);
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
      List<Pet> pets = petService.findPetsByOwnerID(ownerId);
      List<PetDTO> petDTOS = new ArrayList<>();
      pets.forEach(pet -> {
        PetDTO petDTO = new PetDTO(
          pet.getId(),
          pet.getType(),
          pet.getName(),
          ownerId,
          pet.getBirthdate(),
          pet.getNotes());
        petDTOS.add(petDTO);
      });
      return petDTOS;
    }

  private PetDTO copyPetToDTO(Pet pet){
    PetDTO dto = new PetDTO();
    BeanUtils.copyProperties(pet, dto);
    return dto;
  }

  private Pet copyDTOToPet(PetDTO petDTO){
    Pet pet = new Pet();
    BeanUtils.copyProperties(petDTO, pet);
    return pet;
  }

  private List<PetDTO> copyPetsToDTOs (List<Pet> pets) {
    List dtos = new ArrayList<PetDTO>();
    pets.forEach( pet -> {
      dtos.add(copyPetToDTO(pet));
    });
    return dtos;
  }
}
