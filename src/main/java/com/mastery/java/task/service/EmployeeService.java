package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;

import java.util.List;

/**
 * The interface {@code EmployeeService} describes operations(CRUD) with {@link Employee}
 *
 * @author Roman Aleksandrov
 * @version 1.0
 */
public interface EmployeeService {

    /**
     * Get employees list
     *
     * @return {@link List} of employees
     */
    List<Employee> findAllEmployee();

    /**
     * Save employee
     *
     * @param employee {@link Employee}
     */
    void saveEmployee(Employee employee);

    /**
     * Get employee by id
     *
     * @param id the current employee id
     * @return {@link List} of employees
     */
    Employee getEmployeeById(Integer id);

    /**
     * Update employee by id
     *
     * @param employee {@link Employee}
     * @param id       the current employee id
     */
    void updateById(Employee employee, Integer id);

    /**
     * Delete employee
     *
     * @param id the current employee id
     */
    void removeEmployeeById(Integer id);



    List<Employee> getByName(String name);
}
