package com.mastery.java.task.controller;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.validation.EmployeeValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Api(value = "CRUD operations with  employees database")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService, EmployeeValidator employeeValidator) {
        this.employeeService = employeeService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get list employees", response = ResponseEntity.class)
    @ApiResponse(code = 200, message = "Successful retrieval of employees list", response = Employee.class)
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/employee", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get employees by first name", response = ResponseEntity.class)
    @ApiResponse(code = 200, message = "Successful retrieval of employees by name", response = Employee.class)
    public List<Employee> getByName(@RequestParam("name") final String name) {
        return employeeService.getByName(name);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get employee by id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of employee detail", response = Employee.class),
            @ApiResponse(code = 404, message = "Employee does not exist")})
    public Employee getById(@PathVariable final Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add new employee", response = ResponseEntity.class)
    @ApiResponse(code = 201, message = "Successful save employees", response = Employee.class)
    public void add(@Valid @RequestBody final Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update employee with required id", response = ResponseEntity.class)
    @ApiResponse(code = 200, message = "Successfully change employees", response = Employee.class)
    public void updateById(@Valid @RequestBody final Employee employee, @PathVariable final Integer id) {
        employeeService.updateById(employee, id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete employee by id", response = ResponseEntity.class)
    @ApiResponse(code = 200, message = "Successful delete employee", response = Employee.class)
    public void deleteById(@PathVariable final Integer id) {
        employeeService.removeById(id);
    }
}
