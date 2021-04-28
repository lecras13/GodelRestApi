package com.mastery.java.task.controller;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.validation.EmployeeValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/employees")
@Api(value = "CRUD operations with  employees database")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeValidator employeeValidator;

    public EmployeeController(EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
        this.employeeValidator = employeeValidator;
    }

    @ApiOperation(value = "Get list employees", response = ResponseEntity.class)
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @ApiOperation(value = "Get employees by first name", response = ResponseEntity.class)
    @GetMapping(value = "/employee", produces = "application/json")
    public ResponseEntity<List<Employee>> getByName(@RequestParam("name") final String name) {
        return ResponseEntity.ok(employeeService.getByName(name));
    }

    @ApiOperation(value = "Get employee by id", response = ResponseEntity.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> getById(@PathVariable final Integer id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @ApiOperation(value = "Add new employee", response = ResponseEntity.class)
    @PostMapping(produces = "application/json")
    public ResponseEntity<Employee> add(@Valid @RequestBody final Employee employee, BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult);
        if (!bindingResult.hasErrors()) {
            employeeService.save(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update employee with required id", response = ResponseEntity.class)
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> updateById(@Valid @RequestBody final Employee employee, BindingResult bindingResult, @PathVariable final Integer id) {
        employeeService.updateById(employee, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Delete employee by id", response = ResponseEntity.class)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> deleteById(@PathVariable final Integer id) {
        employeeService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
