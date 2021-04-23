package com.mastery.java.task.service;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.repository.EmployeeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployee() {
        LOGGER.info("Getting all employees");
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        LOGGER.info("Save employee");
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Integer id) {
        LOGGER.info("Getting employee by id");
        return employeeRepository.findById(id);
    }

    @Override
    public void updateById(Employee employee, Integer id) {
        if (employeeRepository.existsById(id)) {
            LOGGER.info("Updating employee by id");
            employee.setEmployeeId(id);
            employeeRepository.save(employee);
        }
     //   LOGGER.info("Can't updating employee by id because employee not found");
    }

    @Override
    public boolean removeEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
        LOGGER.info("Deleting employee by id");
        return employeeRepository.existsById(id);
    }
}
