package com.mastery.java.task.service;

import com.mastery.java.task.repository.EmployeeRepository;
import com.mastery.java.task.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void updateById(Employee employee, Integer id) {
        if(employeeRepository.existsById(id)){
            employee.setEmployeeId(id);
            employeeRepository.save(employee);
        }
    }

    @Override
    public void removeEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }
}
