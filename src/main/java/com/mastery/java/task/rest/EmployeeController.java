package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.exception.handler.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> showAllEmployees() {
        final List<Employee> employees = employeeService.findAllEmployee();

        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee showEmployeeById(@PathVariable Integer id)  {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        return employee.orElseThrow(
                ()-> new EmployeeNotFoundException("Such id = " + id + " not found!"));
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<?> updateEmployeeById(@RequestBody Employee employee, @PathVariable Integer id) {
        employeeService.updateById(employee, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Integer id) {
        boolean deletedStatus = employeeService.removeEmployeeById(id);
        return deletedStatus
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
