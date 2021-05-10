package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.Exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.Exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    public List<Long> idsFromEntity(List<?> s){
      List<Long> ids = new ArrayList<>();

      return ids;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) throws EmployeeNotFoundException, PetNotFoundException {
        Schedule schedule = scheduleService.findSchedule(scheduleDTO.getId()).orElseGet(Schedule::new);
        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivies(scheduleDTO.getActivities());
        schedule.setEmployees(employeeService.findEmployees(scheduleDTO.getEmployeeIds()));
        schedule.setPets(petService.findAllPetsById(scheduleDTO.getPetIds()));
        schedule = scheduleService.persist(schedule);
      scheduleDTO.setDate(schedule.getDate());
      scheduleDTO.setActivities(schedule.getActivies());

      scheduleDTO.getEmployeeIds().forEach(employeeId -> {
        employeeIds.add(employeeId);
      });

      scheduleDTO.getPetIds().forEach(petId -> {
        petIds.add(petId);
      });

      scheduleDTO.setPetIds(petIds);
      scheduleDTO.setEmployeeIds(employeeIds);
      scheduleDTO.setId(schedule.getId());

      return scheduleDTO;

    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> allSchedules = scheduleService.findAllSchedules();
        List<ScheduleDTO>scheduleDTOS = new ArrayList<>();
        allSchedules.forEach(schedule -> {
          ScheduleDTO scheduleDTO = new ScheduleDTO();
          List<Long> empIds = new ArrayList<>();
          List<Long> petIds = new ArrayList<>();
          schedule.getEmployees().forEach(employee -> empIds.add(employee.getId()));
          schedule.getPets().forEach(pet -> petIds.add(pet.getId()));
          scheduleDTO.setId(schedule.getId());
          scheduleDTO.setEmployeeIds(empIds);
          scheduleDTO.setDate(schedule.getDate());
          scheduleDTO.setActivities(schedule.getActivies());
          scheduleDTO.setPetIds(petIds);
          scheduleDTOS.add(scheduleDTO);
        });
      System.out.println("=======================================");
      System.out.println(scheduleDTOS);
      System.out.println("=======================================");
        return scheduleDTOS;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
      List<Schedule> schedules = scheduleService.findSchedulesForPet(petId);
      List<ScheduleDTO> scheduleDTOS = new ArrayList<>();

      schedules.forEach(schedule -> {
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        schedule.getPets().forEach(pet -> {
          petIds.add(pet.getId());
        });

        schedule.getEmployees().forEach(employee -> {
          employeeIds.add(employee.getId());
        });

        ScheduleDTO scheduleDTO1 = new ScheduleDTO(
          schedule.getId(),
          employeeIds,
          petIds,
          schedule.getDate(),
          schedule.getActivies());

        scheduleDTOS.add(scheduleDTO1);

      });

      return scheduleDTOS;

    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules =this.scheduleService.findSchedulesForEmployee(employeeId);
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

      schedules.forEach(schedule -> {
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();
        schedule.getPets().forEach(pet -> {
          petIds.add(pet.getId());
        });
        schedule.getEmployees().forEach(employee -> {
          employeeIds.add(employee.getId());
        });
        ScheduleDTO scheduleDTO = new ScheduleDTO(
          schedule.getId(),
          employeeIds,
          petIds,
          schedule.getDate(),
          schedule.getActivies());
        System.out.println("+++this is a things+++");
        System.out.println(scheduleDTO);
        System.out.println("+++this is a things+++");
        scheduleDTOs.add(scheduleDTO);


      });



        System.out.println("@#@#@#@#@#@#@##@#@#@#@#");
      System.out.println(scheduleDTOs);
        System.out.println("@#@#@#@#@#@#@##@#@#@#@#");

      return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findSchedulesForCustomer(customerId);
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();
        List<Long> employeeIds = new ArrayList<>();

        schedules.forEach(schedule -> {

          schedule.getPets().forEach(pet -> {
            petIds.add(pet.getId());
          });

          schedule.getEmployees().forEach(employee -> {
            employeeIds.add(employee.getId());
          });

          ScheduleDTO scheduleDTO = new ScheduleDTO(
            schedule.getId(),
            employeeIds,
            petIds,
            schedule.getDate(),
            schedule.getActivies());
          scheduleDTOS.add(scheduleDTO);
        });
        return scheduleDTOS;
    }

  private ScheduleDTO copyScheduleToDTO(Schedule schedule) {
    ScheduleDTO dto = new ScheduleDTO();
//    BeanUtils.copyProperties(schedule, dto);
//    schedule.getEmployees().forEach(employee -> {dto.getEmployeeIds().add(employee.getId());});
//    schedule.getPets().forEach(pet -> {dto.getPetIds().add(pet.getId());});
    return dto;
  }


}
