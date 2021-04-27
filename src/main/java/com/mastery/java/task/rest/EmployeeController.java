package com.mastery.java.task.rest;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getByName(@RequestParam("name") final String name) {
        return ResponseEntity.ok(employeeService.getByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> add(@RequestBody final Employee employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateById(@RequestBody final Employee employee, @PathVariable final Integer id) {
        employeeService.updateById(employee, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable  final Integer id) {
        employeeService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
