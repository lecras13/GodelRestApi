package com.mastery.java.task.service.jms;

import com.mastery.java.task.entity.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
    private final EmployeeService employeeService;


    public Listener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JmsListener(destination = "queue")
    public void receiveMessage(final Employee employee) {
        employeeService.save(employee);
    }
}


