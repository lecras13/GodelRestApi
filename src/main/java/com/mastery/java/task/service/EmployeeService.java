package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAllEmployee();

    void saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Integer id);

    void  updateById(Employee employee, Integer id);

    void removeEmployeeById(Integer id);
}
