package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;

import java.util.List;


public interface EmployeeService {

    List<Employee> findAll();

    void save(Employee employee);

    Employee getById(Integer id);

    void updateById(Employee employee, Integer id);

    void removeById(Integer id);

    List<Employee> getByName(String name);
}
