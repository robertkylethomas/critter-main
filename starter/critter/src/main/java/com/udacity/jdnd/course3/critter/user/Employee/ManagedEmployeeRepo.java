package com.udacity.jdnd.course3.critter.user.Employee;


import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ManagedEmployeeRepo {

  @PersistenceContext
  EntityManager entityManager;

  public List<Long> findEmployeeIdsWithAllSkillsOnDay(Set<EmployeeSkill> employeeSkillSet, DayOfWeek dayOfWeek){

    String selectStatement = "select e.id " +
      "FROM Employee AS e, Employee_Skill AS es, Day_of_week AS d " +
      "WHERE e.id = es.id " +
      "AND e.id = d.id " +
      "AND es.skill in "+ processSkills(employeeSkillSet) +" AND d.day = " + dayOfWeek.ordinal() + " " +
      "GROUP BY e.id HAVING count(es.skill) = " + employeeSkillSet.size();

    Query selectQuery = entityManager.createNativeQuery(selectStatement);
    List<BigInteger> results = selectQuery.getResultList();
    List<Long> longIds = new ArrayList<>();

    results.forEach(result -> {
      longIds.add(result.longValue());
    });

    return longIds;
  }

  String processSkills(Set<EmployeeSkill> employeeSkillSet){
    String s = "(";
    Iterator<EmployeeSkill> it = employeeSkillSet.iterator();
    while (it.hasNext()){
      s += it.next().ordinal();
      if(it.hasNext()){
        s += ", ";
      }
    }
    s += ")";
    return s;
  }

}
