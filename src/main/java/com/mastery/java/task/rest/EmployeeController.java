package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
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
    public List<Employee> showAllEmployees() {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> showEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return ResponseEntity.of(employee);
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public void updateEmployeeById(@RequestBody Employee employee, @PathVariable Integer id) {
       employeeService.updateById(employee, id);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Integer id) {
        employeeService.removeEmployeeById(id);
    }

}
