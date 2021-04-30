package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Such id = " + id + " not found!"));
    }

    @Override
    public List<Employee> getByNames(String firstName, String lastName) {
        if (lastName == null) {
            return employeeRepository.getByFirstName(firstName);
        }
        return employeeRepository.getByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void updateById(Employee employee, Integer id) {
        if (employeeRepository.existsById(id)) {
            employee.setEmployeeId(id);
            employeeRepository.save(employee);
        }
    }

    @Transactional
    @Override
    public void removeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
