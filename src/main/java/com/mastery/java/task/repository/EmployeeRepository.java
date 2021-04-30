package com.mastery.java.task.repository;

import com.mastery.java.task.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> getByFirstName(String firstName);

    List<Employee> getByFirstNameAndLastName(String firstName, String lastName);

}
